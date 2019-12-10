public class LinkedList {
    private Node head;


    public Node push(int data){
        Node newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
        return newNode;
    }

    public boolean detectLoop(){
        Node pointer = head;
        Node dualPointer = head;

        while (dualPointer != null && dualPointer.getNext() != null) {
            pointer = pointer.getNext();
            dualPointer = dualPointer.getNext().getNext();
            if(pointer == dualPointer) {
                return true;
            }
        }
        return false;
    }

    public Node getHead() {
        return head;
    }

}
