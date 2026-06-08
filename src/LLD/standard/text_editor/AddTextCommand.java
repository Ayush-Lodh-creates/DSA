package LLD.standard.text_editor;

public class AddTextCommand implements Command {

    private Ledger ledger;
    private String text;

    public AddTextCommand(Ledger ledger, String text) {
        this.ledger = ledger;
        this.text = text;
    }

    @Override
    public void execute() {
        ledger.addText(text);
    }

    @Override
    public void undo() {
        ledger.deleteText(text.length());
    }
}
