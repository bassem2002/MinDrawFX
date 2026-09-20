package model.strategies;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger
        implements LoggerStrategy {

    private static final String FILE_NAME =
            "application.log";

    @Override
    public void log(String message) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(
                                     FILE_NAME,
                                     true
                             )
                     )) {

            writer.println(
                    "[FILE LOG] "
                            +
                            message
            );

        } catch (IOException e) {

            System.out.println(
                    "Erreur écriture log fichier"
            );
        }
    }
}