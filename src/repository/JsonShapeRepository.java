package repository;

import factory.ShapeFactory;
import factory.ShapeFactoryProducer;
import factory.ShapeType;

import javafx.scene.paint.Color;

import model.shapes.AbstractShape;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class JsonShapeRepository
        implements ShapeRepository {

    private static final String FILE_NAME =
            "shapes.txt";

    @Override
    public void save(
            List<AbstractShape> shapes
    ) {

        try (

                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        FILE_NAME
                                )
                        )

        ) {

            for (AbstractShape shape : shapes) {

                writer.write(
                        shape.toDataString()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<AbstractShape> load() {

        List<AbstractShape> shapes =
                new ArrayList<>();

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {

            return shapes;
        }

        try (

                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(
                                        FILE_NAME
                                )
                        )

        ) {

            String line;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                String[] data =
                        line.split(";");

                ShapeType type =
                        ShapeType.valueOf(
                                data[0]
                        );

                double x =
                        Double.parseDouble(
                                data[1]
                        );

                double y =
                        Double.parseDouble(
                                data[2]
                        );

                double width =
                        Double.parseDouble(
                                data[3]
                        );

                double height =
                        Double.parseDouble(
                                data[4]
                        );

                Color color =
                        Color.web(
                                data[5]
                        );

                ShapeFactory factory =
                        ShapeFactoryProducer
                                .getFactory(type);

                AbstractShape shape =
                        factory.createShape(
                                x,
                                y,
                                width,
                                height,
                                color,
                                3
                        );

                shapes.add(shape);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return shapes;
    }
}