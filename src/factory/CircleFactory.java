package factory;

import javafx.scene.paint.Color;

import model.shapes.AbstractShape;
import model.shapes.CircleShape;

public class CircleFactory
        implements ShapeFactory {

    @Override
    public AbstractShape createShape(
            double x,
            double y,
            double width,
            double height,
            Color color,
            double strokeWidth
    ) {

        return new CircleShape(
                x,
                y,
                width,
                height,
                color,
                strokeWidth
        );
    }
}