package Unit5.LearningJournal;

import java.lang.*;
import java.util.Objects;

public class HashTable {

    private int size;
    private Node[] table;

    public HashTable(int size) {
        table = new Node[size];
    }


    // Linked list
    private static class Node {

        private int hash;
        private String key;
        private String value;
        private Node next;

        public Node(String key, String value, Integer hash, Node next) {
            this.key = key;
            this.value = value;
            this.hash = key.hashCode();
            this.next = next;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }
    }


    /**
     *
     * @return the number of keys in this hashtable.
     */
    public int size() {
        return size;
    }

    public String get(String key) {
        Node[] tab = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length; //ensure that the number is positive.
        for (Node e = tab[index]; e != null; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    public String put(String key, String value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        Node[] tab = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        Node entry = tab[index];

        // Makes sure the key is not already in the hashtable.
        for (; entry != null; entry = entry.next) {
            if ((entry.hash == hash) && entry.key.equals(key)) {
                String old = entry.value;
                entry.value = value;
                return old;
            }
        }

        // Creates the new entry.
        Node e = tab[index];
        tab[index] = new Node(key, value, hash, entry);
        size++;
        return null;
    }

    public String remove(String key) {
        Node[] tab = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;

        Node e = tab[index];
        for (Node prev = null; e != null; prev = e, e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tab[index] = e.next;
                }
                size--;
                String oldValue = e.value;
                e.value = null;
                return oldValue;
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        Node[] tab = table;
        int hash = key.hashCode();
        if (tab != null && tab.length != 0) {
            int index = (hash & 0x7FFFFFFF) % tab.length;
            for (Node e = tab[index]; e != null; e = e.next) {
                if ((e.hash == hash) && e.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }
}
