package command;

import model.commands.Command;

import model.observers.Observer;
import model.observers.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandManager implements Subject {

    private final Stack<Command> undoStack =
            new Stack<>();

    private final Stack<Command> redoStack =
            new Stack<>();

    private final List<Observer> observers =
            new ArrayList<>();

    public void executeCommand(Command command) {

        command.execute();

        undoStack.push(command);

        redoStack.clear();

        notifyObservers(
                "Commande exécutée"
        );
    }

    public void undo() {

        if (!undoStack.isEmpty()) {

            Command command = undoStack.pop();

            command.undo();

            redoStack.push(command);

            notifyObservers(
                    "Undo effectué"
            );
        }
    }

    public void redo() {

        if (!redoStack.isEmpty()) {

            Command command = redoStack.pop();

            command.execute();

            undoStack.push(command);

            notifyObservers(
                    "Redo effectué"
            );
        }
    }

    @Override
    public void addObserver(
            Observer observer
    ) {

        observers.add(observer);
    }

    @Override
    public void removeObserver(
            Observer observer
    ) {

        observers.remove(observer);
    }

    @Override
    public void notifyObservers(
            String event
    ) {

        for (Observer observer : observers) {

            observer.update(event);
        }
    }
}