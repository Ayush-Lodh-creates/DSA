package LLD.standard.text_editor;

public class DLL {
    char ch;
    DLL next;
    DLL prev;

    DLL(char ch) {
        this.ch = ch;
        this.prev = null;
        this.next = null;
    }
}
