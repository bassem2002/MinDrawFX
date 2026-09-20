package controller;

import command.CommandManager;

import decorator.DashedDecorator;
import decorator.GlowDecorator;
import decorator.RedBorderDecorator;
import decorator.ShadowDecorator;
import decorator.ThickBorderDecorator;

import factory.CircleFactory;
import factory.LineFactory;
import factory.RectangleFactory;
import factory.ShapeFactory;
import factory.ShapeType;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;

import model.commands.DeleteShapeCommand;
import model.commands.DrawShapeCommand;

import model.observers.Observer;

import model.shapes.AbstractShape;

import model.strategies.ConsoleLogger;

import repository.ShapeRepository;

import service.ExportService;
import service.LoggingService;
import service.ShapePersistenceService;
import service.ShapeService;

import factory.RepositoryFactory;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class MainController
        implements Initializable,
        Observer {

    @FXML
    private Canvas canvas;

    @FXML
    private Label statusLabel;

    @FXML
    private ListView<String> historyList;

    @FXML
    private Button rectangleButton;

    @FXML
    private Button circleButton;

    @FXML
    private Button lineButton;

    private GraphicsContext gc;

    private final ShapeService shapeService =
            new ShapeService();

    private final CommandManager commandManager =
            new CommandManager();

    private final LoggingService loggingService =
            new LoggingService(
                    new ConsoleLogger()
            );

    private final ShapeRepository repository =
            RepositoryFactory.createRepository(
                    "sqlite"
            );

    private final ShapePersistenceService
            persistenceService =
            new ShapePersistenceService(
                    repository
            );

    private final ExportService exportService =
            new ExportService();

    private AbstractShape previewShape;

    private AbstractShape selectedShape;

    private ShapeType currentShapeType =
            ShapeType.RECTANGLE;

    private Button activeButton;

    private double startX;

    private double startY;

    @Override
    public void initialize(
            URL url,
            ResourceBundle resourceBundle
    ) {

        commandManager.addObserver(this);

        gc =
                canvas.getGraphicsContext2D();

        activateButton(
                rectangleButton
        );

        initializeCanvasEvents();

        redraw();

        update(
                "Application prête"
        );
    }

    private void initializeCanvasEvents() {

        canvas.addEventHandler(
                MouseEvent.MOUSE_PRESSED,
                e -> {

                    startX = e.getX();

                    startY = e.getY();

                    selectedShape = null;

                    for (AbstractShape shape :
                            shapeService.getShapes()) {

                        shape.setSelected(false);

                        if (
                                shape.contains(
                                        e.getX(),
                                        e.getY()
                                )
                        ) {

                            selectedShape = shape;

                            shape.setSelected(true);
                        }
                    }

                    redraw();
                }
        );

        canvas.addEventHandler(
                MouseEvent.MOUSE_DRAGGED,
                e -> {

                    if (selectedShape != null) {

                        return;
                    }

                    double currentX =
                            e.getX();

                    double currentY =
                            e.getY();

                    previewShape =
                            createCurrentShape(
                                    currentX,
                                    currentY
                            );

                    redraw();
                }
        );

        canvas.addEventHandler(
                MouseEvent.MOUSE_RELEASED,
                e -> {

                    if (selectedShape != null) {

                        return;
                    }

                    double endX =
                            e.getX();

                    double endY =
                            e.getY();

                    previewShape =
                            createCurrentShape(
                                    endX,
                                    endY
                            );

                    DrawShapeCommand command =
                            new DrawShapeCommand(
                                    shapeService,
                                    previewShape
                            );

                    commandManager.executeCommand(
                            command
                    );

                    previewShape = null;

                    redraw();

                    update(
                            currentShapeType
                                    + " ajouté"
                    );
                }
        );
    }

    private AbstractShape createCurrentShape(
            double endX,
            double endY
    ) {

        ShapeFactory factory =
                getFactoryForCurrentShape();

        return factory.createShape(
                startX,
                startY,
                endX - startX,
                endY - startY,
                getColorForShape(),
                3
        );
    }

    private ShapeFactory getFactoryForCurrentShape() {

        switch (currentShapeType) {

            case RECTANGLE:

                return new RectangleFactory();

            case CIRCLE:

                return new CircleFactory();

            case LINE:

                return new LineFactory();

            default:

                throw new IllegalArgumentException(
                        "Factory introuvable"
                );
        }
    }

    @FXML
    private void handleRectangle() {

        currentShapeType =
                ShapeType.RECTANGLE;

        activateButton(
                rectangleButton
        );

        update(
                "Rectangle sélectionné"
        );
    }

    @FXML
    private void handleCircle() {

        currentShapeType =
                ShapeType.CIRCLE;

        activateButton(
                circleButton
        );

        update(
                "Circle sélectionné"
        );
    }

    @FXML
    private void handleLine() {

        currentShapeType =
                ShapeType.LINE;

        activateButton(
                lineButton
        );

        update(
                "Line sélectionnée"
        );
    }

    @FXML
    private void handleUndo() {

        commandManager.undo();

        redraw();

        update(
                "Undo effectué"
        );
    }

    @FXML
    private void handleRedo() {

        commandManager.redo();

        redraw();

        update(
                "Redo effectué"
        );
    }

    @FXML
    private void handleDelete() {

        if (selectedShape == null) {

            update(
                    "Sélectionnez une forme"
            );

            return;
        }

        DeleteShapeCommand command =
                new DeleteShapeCommand(
                        shapeService,
                        selectedShape
                );

        commandManager.executeCommand(
                command
        );

        selectedShape = null;

        redraw();

        update(
                "Forme supprimée"
        );
    }

    @FXML
    private void handleSave() {

        persistenceService.saveShapes(
                shapeService.getShapes()
        );

        update(
                "Projet sauvegardé"
        );
    }

    @FXML
    private void handleLoad() {

        shapeService.setShapes(
                persistenceService.loadShapes()
        );

        selectedShape = null;

        redraw();

        update(
                "Projet chargé"
        );
    }

    @FXML
    private void handleClear() {

        shapeService.clearShapes();

        selectedShape = null;

        redraw();

        update(
                "Canvas vidé"
        );
    }

    @FXML
    private void handleExport() {

        exportService.exportCanvasAsPNG(
                canvas
        );

        update(
                "Canvas exporté"
        );
    }

    @FXML
    private void handleExit() {

        System.exit(0);
    }

    @FXML
    private void toggleGlow() {

        applyDecorator(
                "glow"
        );
    }

    @FXML
    private void toggleShadow() {

        applyDecorator(
                "shadow"
        );
    }

    @FXML
    private void toggleRedBorder() {

        applyDecorator(
                "red"
        );
    }

    @FXML
    private void toggleDashed() {

        applyDecorator(
                "dashed"
        );
    }

    @FXML
    private void toggleBold() {

        applyDecorator(
                "bold"
        );
    }

    private void applyDecorator(
            String type
    ) {

        if (selectedShape == null) {

            update(
                    "Sélectionnez une forme"
            );

            return;
        }

        AbstractShape decoratedShape =
                selectedShape;

        switch (type) {

            case "glow":

                decoratedShape =
                        new GlowDecorator(
                                decoratedShape
                        );

                break;

            case "shadow":

                decoratedShape =
                        new ShadowDecorator(
                                decoratedShape
                        );

                break;

            case "red":

                decoratedShape =
                        new RedBorderDecorator(
                                decoratedShape
                        );

                break;

            case "dashed":

                decoratedShape =
                        new DashedDecorator(
                                decoratedShape
                        );

                break;

            case "bold":

                decoratedShape =
                        new ThickBorderDecorator(
                                decoratedShape
                        );

                break;
        }

        List<AbstractShape> shapes =
                shapeService.getShapes();

        for (int i = 0;
             i < shapes.size();
             i++) {

            if (
                    shapes.get(i)
                            == selectedShape
            ) {

                shapes.set(
                        i,
                        decoratedShape
                );

                break;
            }
        }

        selectedShape =
                decoratedShape;

        selectedShape.setSelected(true);

        redraw();

        update(
                type + " appliqué"
        );
    }

    private void activateButton(
            Button button
    ) {

        if (activeButton != null) {

            activeButton
                    .getStyleClass()
                    .remove(
                            "active-tool-button"
                    );
        }

        activeButton = button;

        activeButton
                .getStyleClass()
                .add(
                        "active-tool-button"
                );
    }

    private Color getColorForShape() {

        switch (currentShapeType) {

            case RECTANGLE:

                return Color.DODGERBLUE;

            case CIRCLE:

                return Color.CRIMSON;

            case LINE:

                return Color.LIMEGREEN;

            default:

                return Color.BLACK;
        }
    }

    private void redraw() {

        gc.clearRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );

        gc.setFill(
                Color.web("#F4F4F4")
        );

        gc.fillRoundRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight(),
                20,
                20
        );

        for (AbstractShape shape :
                shapeService.getShapes()) {

            shape.draw(gc);
        }

        if (previewShape != null) {

            previewShape.draw(gc);
        }
    }

    @Override
    public void update(
            String event
    ) {

        historyList
                .getItems()
                .add(0, event);

        statusLabel.setText(event);

        loggingService.log(event);
    }
}