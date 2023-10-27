package bnmh.ca2.utils;

import java.io.Serializable;

public class LinkedNode<B> implements Serializable {

    private LinkedNode<B> head = null;
    private LinkedNode<B> next = null;
    private B contents;

    public LinkedNode<B> getHead() {
        return head;
    }

    public B getContents() {
        return contents;
    }

    public LinkedNode<B> getNext() {
        return next;
    }

    public void setHead(LinkedNode<B> head) {
        this.head = head;
    }

    public void setNext(LinkedNode<B> next) {
        this.next = next;
    }

    public void setContents(B contents) {
        this.contents = contents;
    }

}
