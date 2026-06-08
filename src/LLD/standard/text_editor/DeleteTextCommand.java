package LLD.standard.text_editor;

public class DeleteTextCommand implements Command {

    private Ledger ledger;
    private int letters;
    private String deletedText;

    public DeleteTextCommand(Ledger ledger, int letters) {
        this.ledger = ledger;
        this.letters = letters;
    }

    @Override
    public void execute() {
        this.deletedText = ledger.deleteText(letters);
    }

    @Override
    public void undo() {
        ledger.addText(deletedText);
    }
}
