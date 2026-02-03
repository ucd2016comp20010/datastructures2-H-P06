package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> header;
    private Node<E> trailer;

    private int size = 0;

    public DoublyLinkedList() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.next = trailer;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
        Node<E> newNode = new  Node<E>(e, pred, succ);
        pred.next = newNode;
        succ.prev = newNode;
        size++;
        return;
    }

    @Override
    public int size() {
        // TODO
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        if(size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public E get(int i) {
        // TODO
        if(size == 0 || i < 0 || i >= size) {
            return null;
        }

        Node<E> curr = header;
        for(int j = 0; j <= i; j++){
            curr = curr.getNext();
        }

        return curr.getData();
    }

    @Override
    public void add(int i, E e) {
        if(size == 0) {
            addBetween(e, header, trailer);
            return;
        }

        Node<E> oldHead = header;
        if(i == 0) {
            Node<E> newNode = new Node<E>(e, null, oldHead);
            header.prev = newNode;
            header = newNode;
            return;
        }

    }

    @Override
    public E remove(int i) {
        // TODO
        return null;
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) header.next;

        @Override
        public boolean hasNext() {
            return curr != trailer;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        // TODO
        return null;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return header.next.getData();
    }

    public E last() {
        if(isEmpty()) {
            return null;
        }
        return trailer.prev.getData();
    }

    @Override
    public E removeFirst() {
        // TODO
        if(size == 0){
            return null;
        }

        Node<E> curr = header.next;
        header.next = curr.next;
        curr.next.prev = header;
        curr.next = null;
        curr.prev = null;
        size--;
        return curr.getData();
    }

    @Override
    public E removeLast() {
        // TODO
        if(size == 0){
            return null;
        }

        Node<E> curr = trailer.prev;
        curr.prev.next = trailer;
        trailer.prev = curr.prev;


        curr.prev = null;
        curr.next = null;
        size--;
        return curr.getData();
    }

    @Override
    public void addLast(E e) {
        // TODO
        if(size  == 0) {
            addFirst(e);
            return;
        }

        Node<E> oldtail = trailer.prev;
        Node<E> newNode = new Node<E>(e, oldtail, trailer);
        trailer.prev.next = newNode;
        oldtail.prev = newNode;

    }

    @Override
    public void addFirst(E e) {
        // TODO
        if(size == 0) {
            Node<E> newNode = new Node<E>(e, header, trailer);
            header.next = newNode;
            trailer.prev = newNode;
            size++;
            return;
        }

        Node<E> newNode = new Node<E>(e, header, header.next);
        header.next.prev = newNode;
        header.next = newNode;

        size++;
        return;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = header.next;
        while (curr != trailer) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != trailer) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addLast(100);
        ll.addLast(200);
        System.out.println(ll);

        System.out.println("removed " + ll.removeFirst());
        System.out.println(ll);

        System.out.println("removed " + ll.removeLast());
        System.out.println(ll);

    }
}