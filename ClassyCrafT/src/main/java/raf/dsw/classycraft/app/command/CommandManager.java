package raf.dsw.classycraft.app.command;

import raf.dsw.classycraft.app.core.ApplicationFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandManager{
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        if(command instanceof MoveCommand) // Combine move commands on same set of elements into one
        {
            if(!undoStack.empty() && undoStack.peek() instanceof MoveCommand)
            {
                var prevCmd = (MoveCommand) undoStack.peek();
                if(prevCmd.getSelectedPainters().equals(((MoveCommand) command).getSelectedPainters()))
                {
                    command = new MoveCommand(prevCmd, (MoveCommand) command);
                    undoStack.pop();
                }
            }
        }
        undoStack.push(command);
        redoStack.clear(); // Clear redo stack on new command execution
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
        }
    }
}
