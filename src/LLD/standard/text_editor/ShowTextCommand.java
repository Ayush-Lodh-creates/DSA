package LLD.standard.text_editor;

public class ShowTextCommand implements Command {

    private Ledger ledger;

    public ShowTextCommand(Ledger ledger) {
        this.ledger = ledger;
    }

    @Override
    public void execute() {
        ledger.showText();
    }

    @Override
    public void undo() {
        // No undo method is possible
    }
}
