package model.shapes;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape
        extends AbstractShape {

    public RectangleShape(
            double x,
            double y,
            double width,
            double height,
            Color color,
            double strokeWidth
    ) {

        super(
                x,
                y,
                width,
                height,
                color,
                strokeWidth
        );
    }

    @Override
    public void draw(
            GraphicsContext gc
    ) {

        gc.setStroke(color);

        gc.setLineWidth(strokeWidth);

        gc.strokeRect(
                x,
                y,
                width,
                height
        );

        if (selected) {

            gc.setStroke(Color.ORANGE);

            gc.setLineWidth(2);

            gc.strokeRect(
                    x - 5,
                    y - 5,
                    width + 10,
                    height + 10
            );
        }
    }

    @Override
    public boolean contains(
            double px,
            double py
    ) {

        return px >= x
                && px <= x + width
                && py >= y
                && py <= y + height;
    }

    @Override
    public ShapeType getType() {

        return ShapeType.RECTANGLE;
    }
}