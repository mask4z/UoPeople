package Unit3.DiscussionPost;

import java.util.Arrays;

/**
 * An array implementation of the stack ADT.
 */
public class StackOfStringsArray {

    private String[] items = new String[10];  // Holds the items on the stack.
    private int top = 0;  // The number of items currently on the stack.

    /**
     * Add N to the top of the stack.
     */
    public void push(String N) {
        if (top == items.length) {
            // The array is full, so make a new, larger array and
            // copy the current stack items into it.
            items = Arrays.copyOf(items, 2 * items.length);
        }
        items[top] = N;  // Put N in next available spot.
        top++;           // Number of items goes up by one.
    }

    /**
     * Remove the top item from the stack, and return it.
     * Throws an IllegalStateException if the stack is empty when
     * this method is called.
     */
    public String pop() {
        if (isEmpty())
            throw new IllegalStateException("Can't pop from an empty stack.");
        String topItem = items[top - 1];  // Top item in the stack.
        top--;    // Number of items on the stack goes down by one.
        return topItem;
    }

    /**
     * Returns true if the stack is empty.  Returns false
     * if there are one or more items on the stack.
     */
    public boolean isEmpty() {
        return (top == 0);
    }
} // end of StackOfStringsArray class
