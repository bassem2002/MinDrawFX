package model.strategies;

public class LoggingContext {

    private LoggerStrategy strategy;

    public LoggingContext(
            LoggerStrategy strategy
    ) {

        this.strategy = strategy;
    }

    public void setStrategy(
            LoggerStrategy strategy
    ) {

        this.strategy = strategy;
    }

    public void executeLog(
            String message
    ) {

        strategy.log(message);
    }
}