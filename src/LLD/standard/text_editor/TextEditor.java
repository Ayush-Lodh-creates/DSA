package LLD.standard.text_editor;

import java.util.Stack;

public class TextEditor {

    private Ledger ledger;
    private Stack<Command> history;

    public TextEditor(Ledger ledger) {
        this.ledger = ledger;
        history = new Stack<>();
    }

    public void add(String text) {
        Command command = new AddTextCommand(ledger, text);
        command.execute();
        history.push(command);
    }

    public void delete(int num) {
        Command command = new DeleteTextCommand(ledger, num);
        command.execute();
        history.push(command);
    }

    public void show() {
        Command command = new ShowTextCommand(ledger);
        command.execute();
    }

    public void undo(int num) {
        while(!history.isEmpty() && num-- > 0) {
            history.pop().undo();
        }
    }
}
