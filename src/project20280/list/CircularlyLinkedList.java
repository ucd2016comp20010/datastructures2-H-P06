package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;
import java.util.stream.IntStream;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
        Node<E> tail = new Node<E>(null, null);
        tail.setNext(tail);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if(size == 0){
            return null;
        }

        if(i >= size){
            return null;
        }

        Node<E> curr = tail.next;

        for(int j = 0; j < i; j++){
            curr = curr.next;
        }

        return curr.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        // TODO
        if(size == 0){
            addFirst(e);
            return;
        }

        if(size < 0|| i > size){
            return;
        }

        if(i == size){
            addLast(e);
            return;
        }

        Node<E> curr =  tail.next;
        for(int j = 0; j < i - 1; j++){
            curr = curr.next;
        }

        Node<E> newNode = new Node<>(e, curr.getNext());
        curr.setNext(newNode);
        size++;
        return;
    }

    @Override
    public E remove(int i) {
        // TODO
        if(size == 0){
            return null;
        }

        Node<E> curr = tail.next;
        for(int j = 0; j < i-1; j++){
            curr = curr.next;
        }

        Node<E> removed = curr.next;
        curr.setNext(curr.next.next);
        size--;
        return removed.getData();
    }

    public void rotate() {
        // TODO
        if(size == 0){
            return;
        }

        //this works fine
        if(size == 2){
            Node<E> temp = tail;
            tail = tail.next;
            tail.next = temp;
            return;
        }

        Node <E> head = tail.next;
        tail = tail.next;
        head = head.next;

        return;
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
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
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // TODO
        if(size == 0){
            return null;
        }

        Node<E> toRemove = tail.next;
        tail.next = tail.next.next;
        size--;
        return toRemove.getData();
    }

    @Override
    public E removeLast() {
        // TODO
        if(size == 0){
            return null;
        }

        Node<E> toRemove = tail;
        Node<E> curr = tail.next;
        tail = toRemove.next;
        for(int i = 0; i < size; i++){
            curr = curr.next;
        }

        curr.next = tail;
        size--;
        return toRemove.getData();
    }

    @Override
    public void addFirst(E e) {
        // TODO
        if(size == 0){
            tail = new Node<>(e, null);
            tail.setNext(tail);
            size++;
            return;
        }

        Node<E> newNode = new Node<>(e, null);
        newNode.next = tail.next;
        tail.next = newNode;
        size++;
        return;
    }

    @Override
    public void addLast(E e) {
        // TODO
        if(size == 0){
            tail = new Node<>(e, null);
            tail.next = tail;
            size++;
            return;
        }

        Node<E> newNode = new Node<>(e, tail.next);
        tail.next = newNode;
        tail = newNode;
        size++;
        return;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
//        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
//        ll.addFirst(1);
//        ll.addLast(2);
//        ll.addLast(3);
//

        CircularlyLinkedList<Integer> ll2 = new CircularlyLinkedList<Integer>();
        IntStream.rangeClosed(0, 10).forEach((x) -> ll2.addLast(x));
        System.out.println(ll2.toString());


    }
}
