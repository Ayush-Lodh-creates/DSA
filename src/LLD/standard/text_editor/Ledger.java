package LLD.standard.text_editor;

public class Ledger {
    private static Ledger instance = null;
    private DLL head;
    private DLL tail;
    private DLL pointer;
    private int size = 0;

    private Ledger() {
        this.head = new DLL('*');
        this.tail = new DLL('*');
        head.next = tail;
        tail.prev = head;
        this.pointer = head;
        this.size = 0;
    }

    public static Ledger getInstance() {
        if(instance == null) {
            instance = new Ledger();
        }
        return instance;
    }

    public void addText(String text) {
        for(char ch : text.toCharArray()) {
            DLL newNode = new DLL(ch);
            DLL nextPointer = pointer.next;
            newNode.prev = pointer;
            newNode.next = nextPointer;
            pointer.next = newNode;
            nextPointer.prev = newNode;
            pointer = newNode;
            size++;
        }
    }

    public String deleteText(int letters) {
        DLL nextPointer = pointer.next;
        StringBuilder sb = new StringBuilder();
        while(pointer != head && letters-- > 0) {
            sb.append(pointer.ch);
            pointer = pointer.prev;
            size--;
        }
        pointer.next = nextPointer;
        nextPointer.prev = pointer;
        return sb.reverse().toString();
    }

    public void showText() {
        DLL temp = head.next;
        StringBuilder sb = new StringBuilder();
        if(size == 0) {
            System.out.println(sb.toString());
        }
        while(temp.next != null) {
            sb.append(temp.ch);
            temp = temp.next;
        }
        System.out.println(sb.toString());
    }
}
