package factory;

public class ShapeFactoryProducer {

    private ShapeFactoryProducer() {

    }

    public static ShapeFactory getFactory(
            ShapeType type
    ) {

        switch (type) {

            case RECTANGLE:
                return new RectangleFactory();

            case CIRCLE:
                return new CircleFactory();

            case LINE:
                return new LineFactory();

            default:
                throw new IllegalArgumentException(
                        "Type de forme invalide"
                );
        }
    }
}