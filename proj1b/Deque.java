public interface Deque<T> {

    /** Adds an item of type T to the front of the deque */
    void addFirst(T item);

    /** Adds an item of type T to the back of the deque */
    void addLast(T item);

    /** Removes and returns the item at the front of the deque */
    T removeFirst();

    /** Removes and returns the item at the end of the deque */
    T removeLast();

    /** Returns true if deque is empty, false otherwise */
    boolean isEmpty();

    /** Returns the number of items in the deque */
    public int size();

    /** Recursion version of get */
    T get(int index);

    /** Prints the items in the deque from first to last, separated by a space */
    void printDeque();
}
