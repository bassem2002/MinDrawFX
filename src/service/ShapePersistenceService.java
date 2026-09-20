package service;

import model.shapes.AbstractShape;

import repository.ShapeRepository;

import java.util.List;

public class ShapePersistenceService {

    private final ShapeRepository repository;

    public ShapePersistenceService(
            ShapeRepository repository
    ) {

        this.repository = repository;
    }

    public void saveShapes(
            List<AbstractShape> shapes
    ) {

        repository.save(shapes);
    }

    public List<AbstractShape> loadShapes() {

        return repository.load();
    }
}