package repository;

import factory.ShapeFactory;
import factory.ShapeFactoryProducer;
import factory.ShapeType;

import javafx.scene.paint.Color;

import model.shapes.AbstractShape;

import singleton.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class SQLiteShapeRepository
        implements ShapeRepository {

    private final Connection connection;

    public SQLiteShapeRepository() {

        connection =
                DatabaseConnection
                        .getInstance()
                        .getConnection();

        createTable();
    }

    private void createTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS shapes (

                    id INTEGER PRIMARY KEY AUTOINCREMENT,

                    type TEXT,

                    x REAL,

                    y REAL,

                    width REAL,

                    height REAL,

                    color TEXT
                )
                """;

        try (

                Statement statement =
                        connection.createStatement()

        ) {

            statement.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void save(
            List<AbstractShape> shapes
    ) {

        String deleteSql =
                "DELETE FROM shapes";

        String insertSql = """
                INSERT INTO shapes(
                    type,
                    x,
                    y,
                    width,
                    height,
                    color
                )
                VALUES(?,?,?,?,?,?)
                """;

        try (

                Statement deleteStatement =
                        connection.createStatement();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                insertSql
                        )

        ) {

            deleteStatement.executeUpdate(
                    deleteSql
            );

            for (AbstractShape shape : shapes) {

                String[] data =
                        shape.toDataString()
                                .split(";");

                preparedStatement.setString(
                        1,
                        data[0]
                );

                preparedStatement.setDouble(
                        2,
                        Double.parseDouble(
                                data[1]
                        )
                );

                preparedStatement.setDouble(
                        3,
                        Double.parseDouble(
                                data[2]
                        )
                );

                preparedStatement.setDouble(
                        4,
                        Double.parseDouble(
                                data[3]
                        )
                );

                preparedStatement.setDouble(
                        5,
                        Double.parseDouble(
                                data[4]
                        )
                );

                preparedStatement.setString(
                        6,
                        data[5]
                );

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<AbstractShape> load() {

        List<AbstractShape> shapes =
                new ArrayList<>();

        String sql =
                "SELECT * FROM shapes";

        try (

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(sql)

        ) {

            while (resultSet.next()) {

                ShapeType type =
                        ShapeType.valueOf(
                                resultSet.getString(
                                        "type"
                                )
                        );

                double x =
                        resultSet.getDouble("x");

                double y =
                        resultSet.getDouble("y");

                double width =
                        resultSet.getDouble("width");

                double height =
                        resultSet.getDouble("height");

                Color color =
                        Color.web(
                                resultSet.getString(
                                        "color"
                                )
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

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return shapes;
    }
}