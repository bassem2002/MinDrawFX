package decorator;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import model.shapes.AbstractShape;

public class GlowDecorator
        extends ShapeDecorator {

    public GlowDecorator(
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

        gc.setStroke(
                Color.web("#00D4FF")
        );

        gc.setLineWidth(16);

        gc.setGlobalAlpha(0.25);

        ShapeType type =
                baseShape.getType();

        switch(type) {

            case RECTANGLE:

                gc.strokeRoundRect(
                        x - 10,
                        y - 10,
                        width + 20,
                        height + 20,
                        20,
                        20
                );

                break;

            case CIRCLE:

                gc.strokeOval(
                        x - 10,
                        y - 10,
                        width + 20,
                        height + 20
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