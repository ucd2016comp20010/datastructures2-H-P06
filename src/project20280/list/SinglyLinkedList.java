package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */

        //constructor
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
//        if(head == null) {
//            return 0;
//        }
//        else{
//            int  size = 0;
//            Node<E> curr = head;
//            while(curr != null) {   //just curr and not curr.getnext() because it skips the first
//                size++;
//                curr = curr.getNext();
//            }
//            return size;
//        }
        //more efficient and less redundant
        return size;
    }

    //@Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public E get(int position) {
        Node<E> curr = head;

        if(position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Position is out of bounds " + position);
        }
        else{
            for(int i = 0; i < position; i++) {
                curr = curr.getNext();
            }
        }

        return curr.getElement();

    }

    @Override
    public void add(int position, E e) {
        //if position is not within range
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Position out of bounds. Given position " + position);
        }

        if(size == 0 || position == 0){
            addFirst(e);
            return;
        }

        //if everything is ok
        Node<E> curr = head; //to iterate through the linked list
        for(int i = 0; i < position - 1; i++){ //stops at the one before
            curr = curr.getNext();
        }

        Node<E> newNode = new Node<>(e, curr.getNext());    //new node points to previous' next
        curr.setNext(newNode);  //previous' next is the new
        size++; //increase the size of the linked list

        return;
    }


    @Override
    public void addFirst(E e) {
        head = new Node<>(e, this.head);
        size++;
        return;
    }

    @Override
    public void addLast(E e) {
        if(size == 0){
            addFirst(e);
            return;
        }

        Node<E> curr = head;
        while(curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(new Node<>(e, null));
        size++;

        return;
    }

    @Override
    public E remove(int position) {
        if(size == 0){
            throw new IndexOutOfBoundsException("The list is empty");
        }

        if(position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Position out of bounds. Given position " + position);
        }

        Node<E> curr = head;
        Node<E> removedNode;

        if(position == 0){
            removedNode = head;
            head =  head.getNext();
        }
        else{
            for(int i = 0; i < position - 1; i++){
                curr = curr.getNext();
            }

            removedNode = curr.getNext();   //we need to return the node removed
            curr.setNext(curr.getNext().getNext()); //getting rid of the node at position given
        }

        size--;
        return removedNode.getElement();
    }


    @Override
    public E removeFirst() {
        if(size == 0){
            return null;
        }

        Node<E> removedNode = head;

        head  = head.getNext();
        size--;

        return removedNode.getElement();
    }

    @Override
    public E removeLast() {
        if(size == 0){
            throw new IndexOutOfBoundsException("The list is empty");
        }

        Node<E> removedNode;

        if (size == 1){
            removedNode = head;
            head = null;
            size--;
            return removedNode.getElement();
        }

        Node<E> curr =  head;

        for(int i = 0; i < size - 2; i++){
            curr = curr.getNext();
        }

        removedNode = curr.getNext();
        curr.setNext(null);
        size--;
        return removedNode.getElement();
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}

//test commit