package model.shapes;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineShape
        extends AbstractShape {

    public LineShape(
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

        gc.strokeLine(
                x,
                y,
                x + width,
                y + height
        );

        if (selected) {

            gc.setFill(Color.ORANGE);

            gc.fillOval(
                    x - 4,
                    y - 4,
                    8,
                    8
            );

            gc.fillOval(
                    x + width - 4,
                    y + height - 4,
                    8,
                    8
            );
        }
    }

    @Override
    public boolean contains(
            double px,
            double py
    ) {

        double distance =
                Math.abs(
                        (height * px)
                                - (width * py)
                                + ((x + width) * y)
                                - ((y + height) * x)
                )
                        /
                        Math.sqrt(
                                height * height
                                        + width * width
                        );

        return distance <= 5;
    }

    @Override
    public ShapeType getType() {

        return ShapeType.LINE;
    }
}