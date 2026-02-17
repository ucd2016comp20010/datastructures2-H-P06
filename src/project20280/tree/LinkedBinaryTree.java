package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */

    //checks if the position given is able to be in the tree
    //and it turns the position into a node
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        // TODO
        if(size!=0){
            return null;
        }

        root = createNode(e, null, null, null);
        size++;
        return root;
    }

    public void insert(E e) {
        // TODO
        if(isEmpty()){
            addRoot(e);
            return;
        }
        else{
            addRecursive(root, e);
        }
    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        if(p == null) {
            p =  createNode(e, null, null, null);
            size++;
            return p;
        }

        //choosing the null side to add to
        if(p.getLeft() == null){
            Node<E> newNode = addRecursive(p.getLeft(), e);
            p.setLeft(newNode);
            newNode.setParent(p);
        }
        else if(p.getRight() == null){
            Node<E> newNode = addRecursive(p.getRight(), e);
            p.setRight(newNode);
            newNode.setParent(p);
        }
        else{//both are not null
            p.setLeft(addRecursive(p.getLeft(), e));    //find where to put e in the subtree
            p.getLeft().setParent(p);   //link to the main tree
        }

        return p;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        if(p == null) {
            return null;
        }

        //if the position is valid, it turns it into a node
        Node<E> pNode = validate(p);

        //theres something in there
        if(pNode.getLeft() != null){
            return null;
        }


        //creating the node with info e
        Node<E> leftNode = createNode(e, pNode, null, null);

        //setting the new node to the left of the parent
        pNode.setLeft(leftNode);
        size++;
        return leftNode;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E>  pNode = validate(p);

        if(pNode.getRight() != null){
            return null;
        }

        Node<E> rightNode = createNode(e, pNode, null, null);
        pNode.setRight(rightNode);
        size++;
        return rightNode;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // TODO

        Node<E> pNode = validate(p);    //at this point it becomes a Node
        E oldElement = pNode.getElement();

        pNode.setElement(e);
        return oldElement;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // TODO
        Node<E> nodeP = validate(p);

        //it isn't a leaf
        if(nodeP.getLeft() != null || nodeP.getRight() != null){
            throw new IllegalArgumentException("This is not a leaf :(");
        }

        //we need to set the root to a node so we can attach it
        //because setLeft expects us to give it a node
        //not a whole tree
        if(!t1.isEmpty()){
            Node<E> t1RootasNode = (Node<E>) t1.root();
            nodeP.setLeft(t1RootasNode);
            t1RootasNode.setParent(nodeP);

            size += t1.size;

            //these shouldn't exist anymore since we merge trees
            t1.root = null;
            t1.size = 0;
        }

        //do the same for t2
        if(!t2.isEmpty()){
            Node<E> t2RootasNode = (Node<E>) t2.root();
            nodeP.setRight(t2RootasNode);
            t2RootasNode.setParent(nodeP);

            size += t2.size;

            //these shouldn't exist anymore since we are merging trees
            t2.root = null;
            t2.size = 0;
        }

        return;
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        // TODO
        Node<E> pNode = validate(p);
        E removedElement = pNode.getElement();

        //check if it has 2 children
        if(pNode.getLeft() != null && pNode.getRight() != null){
            throw new IllegalArgumentException("This is has 2 children :(");
        }

        //if the node is a leaf
        if(pNode.getLeft() == null && pNode.getRight() == null){
            if (pNode == root) {
                root = null;
            } else {
                Node<E> parent = pNode.getParent();
                if (pNode == parent.getLeft()) {
                    parent.setLeft(null);  // Parent's left pointer becomes null
                } else {
                    parent.setRight(null); // Parent's right pointer becomes null
                }
            }

        }

        //dealing with if it's getting the left child
        else if(pNode.getLeft() != null){
            Node<E> child = pNode.getLeft();
            child.setParent(pNode.getParent());

            if (pNode == root) {
                root = child; // If pNode was root, its child is the new root
            } else {
                Node<E> grandparent = pNode.getParent();
                if (pNode == grandparent.getLeft())
                    grandparent.setLeft(child);
                else
                    grandparent.setRight(child);
            }
        }

        //for the right child
        else if(pNode.getRight() != null){
            Node<E> child = pNode.getRight();
            child.setParent(pNode.getParent());

            if (pNode == root) {
                root = child; // If pNode was root, its child is the new root
            } else {
                Node<E> grandparent = pNode.getParent();
                if (pNode == grandparent.getLeft())
                    grandparent.setLeft(child);
                else
                    grandparent.setRight(child);
            }
        }

        //clean up
        pNode.setParent(null);
        pNode.setElement(null);
        pNode.setLeft(null);
        pNode.setRight(null);
        size--;

        return removedElement;
    }

    public String toString() {
        return positions().toString();
    }

    //so you need to only one helper here
    //because the helper recursively calls itself and
    //creates the rest of the tree you just needed to call it to make the root
    public void createLevelOrder(ArrayList<E> l) {
        // TODO
        this.size = 0;
        this.root = createLevelOrderHelper(l,null,0);

    }

    //l is the array list, p is the parent, i is position
    //makes tree from array
    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        // TODO
        if(i >= l.size() || l.get(i) == null){
            return null;
        }

        //creates the node at said position
        Node<E> curr = createNode(l.get(i), p, null, null);
        size++;

        //left child
        curr.setLeft(createLevelOrderHelper(l, curr, 2* i + 1));

        //right child
        curr.setRight(createLevelOrderHelper(l, curr, 2* i + 2));

        return curr;

    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        // TODO
        if(i >= arr.length || arr[i] == null){
            return null;
        }

        //creates the node at said position
        Node<E> curr = createNode(arr[i], p, null, null);
        size++;

        //left child
        curr.setLeft(createLevelOrderHelper(arr, curr, 2* i + 1));

        //right child
        curr.setRight(createLevelOrderHelper(arr, curr, 2* i + 2));

        return curr;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}
