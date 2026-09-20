package model.commands;

import model.shapes.AbstractShape;

import service.ShapeService;

public class DeleteShapeCommand
        implements Command {

    private final ShapeService shapeService;

    private final AbstractShape shape;

    public DeleteShapeCommand(
            ShapeService shapeService,
            AbstractShape shape
    ) {

        this.shapeService = shapeService;

        this.shape = shape;
    }

    @Override
    public void execute() {

        shapeService.removeShape(shape);
    }

    @Override
    public void undo() {

        shapeService.addShape(shape);
    }
}