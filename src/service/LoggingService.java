package service;

import model.strategies.LoggerStrategy;
import model.strategies.LoggingContext;

public class LoggingService {

    private final LoggingContext context;

    public LoggingService(
            LoggerStrategy strategy
    ) {

        this.context =
                new LoggingContext(
                        strategy
                );
    }

    public void setStrategy(
            LoggerStrategy strategy
    ) {

        context.setStrategy(strategy);
    }

    public void log(
            String message
    ) {

        context.executeLog(message);
    }
}