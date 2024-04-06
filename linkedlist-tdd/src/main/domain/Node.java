package main.domain;

public class Node {
    private int data;

    private Node next;

    public Node(Integer data) {
        this.data = data;
    }

    public boolean hasNext(){
        return this.next != null;
    }

    public int getData() {
        return data;
    }

    public void deleteNext() {
        this.next = null;
    }
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
