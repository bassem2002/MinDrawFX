package adapter;

import javafx.embed.swing.SwingFXUtils;

import javafx.scene.canvas.Canvas;

import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class CanvasExportAdapter {

    public void exportCanvas(
            Canvas canvas,
            String fileName
    ) {

        WritableImage image =
                canvas.snapshot(
                        null,
                        null
                );

        File file =
                new File(fileName);

        try {

            ImageIO.write(
                    SwingFXUtils.fromFXImage(
                            image,
                            null
                    ),
                    "png",
                    file
            );

            System.out.println(
                    "Export réussi"
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}