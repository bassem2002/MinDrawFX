package model.shapes;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape
        extends AbstractShape {

    public CircleShape(
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

        gc.strokeOval(
                x,
                y,
                width,
                height
        );

        if (selected) {

            gc.setStroke(Color.ORANGE);

            gc.setLineWidth(2);

            gc.strokeOval(
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

        double centerX =
                x + width / 2;

        double centerY =
                y + height / 2;

        double radius =
                width / 2;

        double dx =
                px - centerX;

        double dy =
                py - centerY;

        return dx * dx + dy * dy
                <= radius * radius;
    }

    @Override
    public ShapeType getType() {

        return ShapeType.CIRCLE;
    }
}