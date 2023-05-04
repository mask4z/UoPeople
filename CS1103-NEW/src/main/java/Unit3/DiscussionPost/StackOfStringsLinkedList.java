package Unit3.DiscussionPost;

/**
 * A Link List implementation of the stack ADT.
 *
 * @author Renaldo Swanepoel.
 */
public class StackOfStringsLinkedList {
    /**
     * An object of type Node holds one of the items in the linked list
     * that represents the stack.
     */
    private static class Node {
        String item;
        Node next;
    }

    private Node top;  // Pointer to the Node that is at the top of the stack.

    /**
     * Returns true if the stack is empty.  Returns false
     * if there are one or more items on the stack.
     */
    public boolean isEmpty() {
        return (top == null);
    }

    /**
     * Remove the top item from the stack, and return it.
     * Throws an IllegalStateException if the stack is empty when
     * this method is called.
     */
    public String pop() {
        if (top == null || isEmpty())
            throw new IllegalStateException("Can't pop from an empty stack.");
        String topItem = top.item;  // The item that is being popped.
        top = top.next;          // The previous second item is now on top.
        return topItem;
    }

    /**
     * Add N to the top of the stack.
     */
    public void push(String N) {
        Node newTop;         // A Node to hold the new item.
        newTop = new Node();
        newTop.item = N;     // Store N in the new Node.
        newTop.next = top;   // The new Node points to the old top.
        top = newTop;        // The new item is now on top.
    }
} // end class StackOfStrings
