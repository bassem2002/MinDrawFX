package service;

import model.shapes.AbstractShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeService {

    private final List<AbstractShape> shapes =
            new ArrayList<>();

    public void addShape(
            AbstractShape shape
    ) {

        shapes.add(shape);
    }

    public void removeShape(
            AbstractShape shape
    ) {

        shapes.remove(shape);
    }

    public void clearShapes() {

        shapes.clear();
    }

    public List<AbstractShape> getShapes() {

        return shapes;
    }

    public void setShapes(
            List<AbstractShape> newShapes
    ) {

        shapes.clear();

        shapes.addAll(newShapes);
    }
}