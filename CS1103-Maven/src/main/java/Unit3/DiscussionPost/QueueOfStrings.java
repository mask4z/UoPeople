package Unit3.DiscussionPost;

/**
 * A Linked List implementation of the queue ADT.
 *
 * @author Renaldo Swanepoel
 */
public class QueueOfStrings {
    /**
     * An object of type Node holds one of the items
     * in the linked list that represents the queue.
     */
    private static class Node {
        String item;
        Node next;
    }

    private Node head = null;  // Points to first Node in the queue.
    private Node tail = null;  // Points to last Node in the queue

    /**
     * Return true if the queue is empty.
     */
    boolean isEmpty() {
        return (head == null);
    }

    /**
     * Add s to the back of the queue.
     */
    public void enqueue(String s) {
        Node newTail = new Node();  // A Node to hold the new item.
        newTail.item = s;
        if (isEmpty()) {
            // The queue was empty.  The new Node becomes the only node in the list.  Since it is both the first and last node, both head and tail point to it.
            head = newTail;
            tail = newTail;
        } else {
            // The new node becomes the new tail of the list.
            // (The head of the list is unaffected.)
            tail.next = newTail;
            tail = newTail;
        }
    }

    /**
     * Remove and return the front item in the queue.
     * Throws an IllegalStateException if the queue is empty.
     */
    public String dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Can't dequeue from an empty queue.");
        String firstItem = head.item;
        head = head.next;  // The previous second item is now first.
        // If we have just removed the last item,
        // then head is null.
        if (isEmpty()) {
            // The queue has become empty.  The Node that was
            // deleted was the tail as well as the head of the
            // list, so now there is no tail.  (Actually, the
            // class would work fine without this step.)
            tail = null;
        }
        return firstItem;
    }

} // end class QueueOfStrings
