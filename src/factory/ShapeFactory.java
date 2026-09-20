package factory;

import javafx.scene.paint.Color;

import model.shapes.AbstractShape;

public interface ShapeFactory {

    AbstractShape createShape(
            double x,
            double y,
            double width,
            double height,
            Color color,
            double strokeWidth
    );
}