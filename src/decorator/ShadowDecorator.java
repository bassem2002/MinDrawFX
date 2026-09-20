package decorator;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import model.shapes.AbstractShape;

public class ShadowDecorator
        extends ShapeDecorator {

    public ShadowDecorator(
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
                Color.rgb(
                        0,
                        0,
                        0,
                        0.25
                )
        );

        gc.setLineWidth(12);

        ShapeType type =
                baseShape.getType();

        switch(type) {

            case RECTANGLE:

                gc.strokeRoundRect(
                        x + 8,
                        y + 8,
                        width,
                        height,
                        20,
                        20
                );

                break;

            case CIRCLE:

                gc.strokeOval(
                        x + 8,
                        y + 8,
                        width,
                        height
                );

                break;

            case LINE:

                gc.strokeLine(
                        x + 5,
                        y + 5,
                        x + width + 5,
                        y + height + 5
                );

                break;
        }

        gc.restore();
    }
}