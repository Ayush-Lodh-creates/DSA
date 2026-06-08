package LLD.standard.text_editor;

public class Main {

    public static void main(String[] args) {

        Ledger ledger = Ledger.getInstance();

        TextEditor textEditor = new TextEditor(ledger);
        textEditor.add("Leetcode is fun");
        textEditor.delete(4);
        textEditor.show();
    }
}
