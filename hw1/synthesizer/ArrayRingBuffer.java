package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ItemIterator implements Iterator<T> {
        public boolean hasNext() {
            return isEmpty();
        }
        public T next() {
            return dequeue();
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (!isFull()) {
            rb[last] = x;
            fillCount++;
            if (last < capacity - 1) {
                last++;
            } else {
                last = 0;
            }
        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (!isEmpty()) {
            T result = rb[first];
            fillCount--;
            if (first < capacity - 1) {
                first++;
            } else {
                first = 0;
            }
            return result;
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (!isEmpty()) {
            return rb[first];
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    public Iterator<T> iterator() {
        return new ItemIterator();
    }
}
