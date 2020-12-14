public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Create an empty Deque */
    public ArrayDeque() {
        items = (T[]) new Object[3];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        nextFirst = movePointer(nextFirst, -1);
        if (nextFirst == nextLast) {
            resize(size + 2);
        }
    }

    /** Adds an item of type T to the end of the deque */
    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        nextLast = movePointer(nextLast, 1);
        if (nextFirst == nextLast) {
            resize(size + 2);
        }
    }

    /** Removes and returns the item at the front of the deque */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = movePointer(nextFirst, 1);
        size--;
        T result = items[nextFirst];
        items[nextFirst] = null;
        if (size + 2 < items.length) {
            resize(size + 2);
        }
        return result;
    }

    /** Removes and returns the item at the end of the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = movePointer(nextLast, -1);
        size--;
        T result = items[nextLast];
        items[nextLast] = null;
        if (size + 2 < items.length) {
            resize(size + 2);
        }
        return result;
    }

    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size == 0;
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
        index = getArrayIndex(index);
        return items[index];
    }

    /** Prints the items in the deque from first to last, separated by a space */
    public void printDeque() {
        if (size == 0) {
            System.out.print(" ");
        }
        int p = movePointer(nextFirst, 1);
        while (p != nextLast) {
            System.out.print(items[p] + " ");
            p = movePointer(p, 1);
        }
    }

    /** Move a pointer */
    private int movePointer(int pointer, int var) {
        pointer += var;
        if (pointer < 0) {
            pointer = items.length - 1;
        }
        if (pointer > items.length - 1) {
            pointer = 0;
        }
        return pointer;
    }

    /** Resize the array */
    private void resize(int capacity) {
        T[] resizedItems = (T[]) new Object[capacity];
        if (nextLast <= nextFirst) {
            System.arraycopy(items, nextFirst, resizedItems, 0, items.length - nextFirst);
            System.arraycopy(items, 0, resizedItems, items.length - nextFirst, nextLast + 1);
            nextFirst = 0;
            nextLast = size + 1;
        } else {
            System.arraycopy(items, nextFirst, resizedItems, 0, nextLast - nextFirst);
            nextFirst = 0;
            nextLast = size + 1;
        }
        items = resizedItems;
    }

    /** Given the index in the Deque, return the index in the array */
    private int getArrayIndex(int index) {
        index = nextFirst + index + 1;
        if (index > items.length - 1) {
            index = index - items.length;
        }
        return index;
    }
}
