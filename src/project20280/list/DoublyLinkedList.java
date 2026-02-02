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

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public E get(int i) {
        if(size == 0 || i < 0 || i >= size){
            return null;
        }

        Node<E> curr = head;
        for(int j = 0; j < i; j++){
            curr = curr.getNext();
        }

        return curr.getData();
    }

    @Override
    public void add(int i, E e) {

        if( i < 0 || i > size){
            return;
        }

        Node<E> newNode = new Node<>(e, null, null);


        if(size == 0){
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        if(i == 0){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return;
        }

        Node<E> curr = head;
        for(int j = 0; j < i - 1; j++){
            curr = curr.getNext();
        }

        Node<E> nextNode = curr.getNext();

        newNode.next = curr.getNext();
        newNode.prev = curr;
        curr.next = newNode;

        //update next previous
        if(nextNode != null){
            nextNode.prev = newNode;
        }
        else{
            tail = newNode;
        }
        size++;
        return;
    }

    @Override
    public E remove(int i) {
        if(i < 0 || i > size){
            return null;
        }

        if(size == 0){
            return null;
        }

        if(i == 0){
            Node<E> removed = head;
            if(size == 1){
                head = null;
                tail = null;
                return removed.getData();
            }

            head = head.next;
            head.prev = null;
            size--;
            return removed.getData();
        }

        Node<E> curr = head;

        //goes to the one before
        for(int j = 0; j < i - 1; j++){
            curr = curr.getNext();
        }

        Node<E> removed = curr.getNext();

        curr.next = removed.next;

        if(removed.next != null){
            removed.next.prev = curr;
        }
        else{
            tail = curr;
        }
        size--;
        return removed.getData();
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

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
        return head.next.getData();
    }

    public E last() {
        // TODO
        return null;
    }

    @Override
    public E removeFirst() {
        // TODO
        return null;
    }

    @Override
    public E removeLast() {
        // TODO
        return null;
    }

    @Override
    public void addLast(E e) {
        // TODO
    }

    @Override
    public void addFirst(E e) {
        // TODO
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}