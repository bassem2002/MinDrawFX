package decorator;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import model.shapes.AbstractShape;

public class DashedDecorator
        extends ShapeDecorator {

    public DashedDecorator(
            AbstractShape decoratedShape
    ) {

        super(decoratedShape);
    }

    @Override
    public void draw(
            GraphicsContext gc
    ) {

        decoratedShape.draw(gc);

        AbstractShape baseShape =
                getBaseShape();

        double x = baseShape.getX();

        double y = baseShape.getY();

        double width =
                baseShape.getWidth();

        double height =
                baseShape.getHeight();

        gc.save();

        gc.setStroke(Color.ORANGE);

        gc.setLineWidth(5);

        gc.setLineDashes(18);

        ShapeType type =
                baseShape.getType();

        switch(type) {

            case RECTANGLE:

                gc.strokeRoundRect(
                        x - 5,
                        y - 5,
                        width + 10,
                        height + 10,
                        20,
                        20
                );

                break;

            case CIRCLE:

                gc.strokeOval(
                        x - 5,
                        y - 5,
                        width + 10,
                        height + 10
                );

                break;

            case LINE:

                gc.strokeLine(
                        x,
                        y,
                        x + width,
                        y + height
                );

                break;
        }

        gc.restore();
    }
}