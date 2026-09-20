package app;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/view/main-view.fxml"
                        )
                );

        Parent root = loader.load();

        Scene scene = new Scene(
                root,
                1450,
                850
        );

        scene.getStylesheets().add(
                getClass()
                        .getResource(
                                "/view/style.css"
                        )
                        .toExternalForm()
        );

        stage.setTitle(
                "MiniDrawFX Professional"
        );

        stage.setMinWidth(1200);

        stage.setMinHeight(750);

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}