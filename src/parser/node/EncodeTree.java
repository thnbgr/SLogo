package parser.node;

/**
 * This class is responsible for providing a tree structure to the syntax trees
 * @author Junho Oh
 * @author Wenshun Liu
 */
public class EncodeTree {
    private Node myHead;

    /**
     * the constructor for the EncodeTree class. This is for 
     * creating a null tree 
     */
    public EncodeTree() {
        myHead = null;
    }
    /**
     * the constructor for the EncodeTree class that sets the 
     * given head to the tree's head
     * @param head - the Node to set the head of the tree to
     */
    public EncodeTree(Node head) {
        myHead = head;
    }
    /**
     * returns the head of the tree
     * @return myHead - the head Node of the tree
     */
    public Node getHead() {
        return myHead;
    }

}