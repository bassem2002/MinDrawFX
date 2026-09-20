package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.JDBC;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private Connection connection;

    private static final String URL =
            "jdbc:sqlite:mindraw.db";

    private DatabaseConnection() {

        try {

            DriverManager.registerDriver(
                    new JDBC()
            );

            connection =
                    DriverManager.getConnection(
                            URL
                    );

            System.out.println(
                    "Connexion SQLite établie"
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static synchronized
    DatabaseConnection getInstance() {

        if (instance == null) {

            instance =
                    new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {

        return connection;
    }
}