package service;

import adapter.CanvasExportAdapter;

import javafx.scene.canvas.Canvas;

public class ExportService {

    private final CanvasExportAdapter adapter =
            new CanvasExportAdapter();

    public void exportCanvasAsPNG(
            Canvas canvas
    ) {

        adapter.exportCanvas(
                canvas,
                "export.png"
        );
    }
}