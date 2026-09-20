package model.commands;

import model.shapes.AbstractShape;

import service.ShapeService;

public class DrawShapeCommand
        implements Command {

    private final ShapeService shapeService;

    private final AbstractShape shape;

    public DrawShapeCommand(
            ShapeService shapeService,
            AbstractShape shape
    ) {

        this.shapeService = shapeService;

        this.shape = shape;
    }

    @Override
    public void execute() {

        shapeService.addShape(shape);
    }

    @Override
    public void undo() {

        shapeService.removeShape(shape);
    }
}