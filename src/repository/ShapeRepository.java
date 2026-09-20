package repository;

import model.shapes.AbstractShape;

import java.util.List;

public interface ShapeRepository {

    void save(
            List<AbstractShape> shapes
    );

    List<AbstractShape> load();
}