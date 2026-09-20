package model.shapes;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {

    void draw(GraphicsContext gc);

    boolean contains(double x, double y);
}