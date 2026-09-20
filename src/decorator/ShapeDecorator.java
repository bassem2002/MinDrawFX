package decorator;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;

import model.shapes.AbstractShape;

public abstract class ShapeDecorator
        extends AbstractShape {

    protected AbstractShape decoratedShape;

    public ShapeDecorator(
            AbstractShape decoratedShape
    ) {

        super(
                decoratedShape.getX(),
                decoratedShape.getY(),
                decoratedShape.getWidth(),
                decoratedShape.getHeight(),
                decoratedShape.getColor(),
                decoratedShape.getStrokeWidth()
        );

        this.decoratedShape =
                decoratedShape;
    }

    @Override
    public void draw(
            GraphicsContext gc
    ) {

        decoratedShape.draw(gc);
    }

    @Override
    public boolean contains(
            double px,
            double py
    ) {

        return decoratedShape.contains(
                px,
                py
        );
    }

    @Override
    public ShapeType getType() {

        return decoratedShape.getType();
    }

    public AbstractShape getBaseShape() {

        if(decoratedShape instanceof ShapeDecorator) {

            return ((ShapeDecorator)
                    decoratedShape)
                    .getBaseShape();
        }

        return decoratedShape;
    }
}