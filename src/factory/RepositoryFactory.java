package factory;

import repository.JsonShapeRepository;
import repository.ShapeRepository;
import repository.SQLiteShapeRepository;

public class RepositoryFactory {

    private RepositoryFactory() {

    }

    public static ShapeRepository createRepository(
            String type
    ) {

        switch (
                type.toLowerCase()
        ) {

            case "sqlite":

                return new SQLiteShapeRepository();

            case "json":

                return new JsonShapeRepository();

            default:

                throw new IllegalArgumentException(
                        "Repository invalide"
                );
        }
    }
}