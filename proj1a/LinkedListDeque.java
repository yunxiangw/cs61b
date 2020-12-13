public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node rest;

        public Node(T item, Node prev, Node rest) {
            this.item = item;
            this.prev = prev;
            this.rest = rest;
        }
    }

    /** Circular sentinel */
    private Node sentinel;
    private int size;

    /** Create an empty Deque */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.rest = sentinel;
    }

    /** Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        sentinel.rest = new Node(item, sentinel, sentinel.rest);
        sentinel.rest.rest.prev = sentinel.rest;
        size++;
    }

    /** Adds an item of type T to the back of the deque */
    public void addLast(T item) {
        sentinel.prev.rest = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.rest;
        size++;
    }

    /** Removes and returns the item at the front of the deque */
    public T removeFirst() {
        if (sentinel.rest == sentinel) {
            return null;
        }
        T item = sentinel.rest.item;

        sentinel.rest = sentinel.rest.rest;
        sentinel.rest.prev = sentinel;
        size--;
        return item;
    }

    /** Removes and returns the item at the end of the deque */
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T item = sentinel.prev.item;

        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.rest = sentinel;
        size--;
        return item;
    }

    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /** Gets the item at the given index */
    public T get(int index) {
        if (index > size - 1 || size == 0) {
            return null;
        }
        Node p = sentinel.rest;
        while(index > 0) {
            p = p.rest;
            index--;
        }
        return p.item;
    }

    /** Recursion version of get */
    public T getRecursive(int index) {
        if (index > size - 1 || size == 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.rest);
    }

    private T getRecursiveHelper(int index, Node first) {
        if (index == 0) {
            return first.item;
        } else {
            return getRecursiveHelper(index-1, first.rest);
        }
    }

    /** Prints the items in the deque from first to last, separated by a space */
    public void printDeque() {
        if (size == 0) {
            System.out.print(" ");
        }
        Node p = sentinel.rest;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.rest;
        }
    }


}
