package model.shapes;

import factory.ShapeType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractShape
        implements Drawable {

    protected double x;

    protected double y;

    protected double width;

    protected double height;

    protected Color color;

    protected double strokeWidth;

    protected boolean selected;

    public AbstractShape(
            double x,
            double y,
            double width,
            double height,
            Color color,
            double strokeWidth
    ) {

        this.x = x;

        this.y = y;

        this.width = width;

        this.height = height;

        this.color = color;

        this.strokeWidth = strokeWidth;
    }

    public abstract void draw(
            GraphicsContext gc
    );

    public abstract boolean contains(
            double px,
            double py
    );

    public abstract ShapeType getType();

    public double getX() {

        return x;
    }

    public double getY() {

        return y;
    }

    public double getWidth() {

        return width;
    }

    public double getHeight() {

        return height;
    }

    public Color getColor() {

        return color;
    }

    public double getStrokeWidth() {

        return strokeWidth;
    }

    public void setX(
            double x
    ) {

        this.x = x;
    }

    public void setY(
            double y
    ) {

        this.y = y;
    }

    public void setWidth(
            double width
    ) {

        this.width = width;
    }

    public void setHeight(
            double height
    ) {

        this.height = height;
    }

    public void setColor(
            Color color
    ) {

        this.color = color;
    }

    public void setStrokeWidth(
            double strokeWidth
    ) {

        this.strokeWidth = strokeWidth;
    }

    public boolean isSelected() {

        return selected;
    }

    public void setSelected(
            boolean selected
    ) {

        this.selected = selected;
    }

    public String toDataString() {

        double normalizedX =
                width < 0
                        ? x + width
                        : x;

        double normalizedY =
                height < 0
                        ? y + height
                        : y;

        double normalizedWidth =
                Math.abs(width);

        double normalizedHeight =
                Math.abs(height);

        return getType()
                + ";"
                + normalizedX
                + ";"
                + normalizedY
                + ";"
                + normalizedWidth
                + ";"
                + normalizedHeight
                + ";"
                + color.toString();
    }
}