package bnmh.ca2.utils;

import java.io.Serializable;

public class GenericLinkedList<B> implements Serializable {

    private LinkedNode<B> head = null;

    public LinkedNode<B> get(int indexToGet) {
        LinkedNode<B> temp = head;
        for(int i = 0; i < indexToGet; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    public void add(B b) {
        LinkedNode<B> temp = new LinkedNode<>();
        temp.setContents(b);
        temp.setNext(head);
        head = temp;
    }

    public void remove(int indexToRemove) {
        if(head == null) {
            return;
        } else if(head.getNext() == null) {
            deleteList();
        } else if(indexToRemove == 0){
            LinkedNode<B> temp = head;
            head = head.getNext();
        } else {
            LinkedNode<B> temp = head;
            for(int i = 1; i < indexToRemove -1; i++) {
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
        }
    }

    public void deleteList() {
        head = null;
    }

    public void list() {
        LinkedNode<B> temp = head;
        while(temp != null) {
            System.out.println(temp.getContents());
            temp = temp.getNext();
        }
    }

    public LinkedNode<B> getHead() {
        return head;
    }

    public void setHead(LinkedNode<B> head) {
        this.head = head;
    }
}





//public class GenericLinkedList<BakedGood, Ingredient, Recipe> {
//
//    private BakedGood bakedGood;
//    private Ingredient ingredient;
//    private Recipe recipe;
//
//
//
//}


/*
public class GenericLinkedList<B> {

    private Node<B> head;

}
*/