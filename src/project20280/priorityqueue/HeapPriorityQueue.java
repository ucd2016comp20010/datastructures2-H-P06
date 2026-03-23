package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    int size = 0;

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        // TODO

    }

    // protected utilities
    protected int parent(int j) {
        // TODO
        return (j-1)/2;
    }

    protected int left(int j) {
        // TODO
        return 2*j+1;
    }

    protected int right(int j) {
        // TODO
        return 2*j+2;
    }

    protected boolean hasLeft(int j) {
        // TODO
        return left(j) < heap.size();
    }

    protected boolean hasRight(int j) {
        // TODO
        return right(j) < heap.size();
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        // TODO
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    protected void upheap(int j) {
        // TODO
        while (j > 0) {
            int p = parent(j);

            if(compare(heap.get(j), heap.get(p)) >= 0){
                break;
            }

            //since this is a minheap, the parent is supposed to be smaller
            //so if you have j bigger than p, j becomes the new parent and
            //the old parent now becomes j
            Entry<K, V> temp = heap.get(j);
            heap.set(j, heap.get(p));
            heap.set(p, temp);
            j = p;
        }

    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
        // TODO

        //if a node was to have a child, it would start with left, so you cannot start with a right child without a left one
        while(hasLeft(j)){
            int leftIndex =  left(j);
            int smaller = leftIndex; //just by default for now

            if(hasRight(j)){
                int rightIndex = right(j);

                //find the biggest child
                if(compare(heap.get(rightIndex), heap.get(leftIndex)) < 0){
                    smaller = rightIndex;
                }
            }

            if(compare(heap.get(smaller), heap.get(j) ) < 0){
                swap(j, smaller);
                j = smaller;
            }
            else{
                break;
            }
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        // TODO
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        //im not changing this anymore
        return heap.size();
        //return size;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */


    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        // TODO
        checkKey(key);
        Entry<K, V> newEntry = new PQEntry<>(key, value);
        heap.add(newEntry);
        size++;
        upheap(heap.size()-1);
        return newEntry;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        // TODO
        if(heap.isEmpty()){
            return null;
        }

        Entry<K, V> min = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        downheap(0);
        return min;
    }

    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

        System.out.println("elements: " + rands);
        System.out.println("after adding elements: " + pq);

        System.out.println("min element: " + pq.min());

        pq.removeMin();
        System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]
    }
}
