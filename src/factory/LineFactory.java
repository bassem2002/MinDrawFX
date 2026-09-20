package factory;

import javafx.scene.paint.Color;

import model.shapes.AbstractShape;
import model.shapes.LineShape;

public class LineFactory
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

        return new LineShape(
                x,
                y,
                width,
                height,
                color,
                strokeWidth
        );
    }
}