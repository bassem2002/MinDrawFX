package decorator;

import javafx.scene.canvas.GraphicsContext;

import model.shapes.AbstractShape;

public class ThickBorderDecorator
        extends ShapeDecorator {

    public ThickBorderDecorator(
            AbstractShape decoratedShape
    ) {

        super(decoratedShape);
    }

    @Override
    public void draw(
            GraphicsContext gc
    ) {

        gc.save();

        gc.setLineWidth(10);

        decoratedShape.draw(gc);

        gc.restore();
    }
}