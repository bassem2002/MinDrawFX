package decorator;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import model.shapes.AbstractShape;

public class RedBorderDecorator
        extends ShapeDecorator {

    public RedBorderDecorator(
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

        gc.setStroke(Color.RED);

        gc.setLineWidth(8);

        ShapeType type =
                baseShape.getType();

        switch(type) {

            case RECTANGLE:

                gc.strokeRoundRect(
                        x - 6,
                        y - 6,
                        width + 12,
                        height + 12,
                        20,
                        20
                );

                break;

            case CIRCLE:

                gc.strokeOval(
                        x - 6,
                        y - 6,
                        width + 12,
                        height + 12
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