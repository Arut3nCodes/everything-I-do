package dataStructures;

public interface IQueue{
    public class EmptyQueueException extends Exception{
    }

    public class FullQueueException extends Exception{
    }

    boolean isEmpty();
    boolean isFull();
    Node dequeue() throws EmptyQueueException;
    void enqueue(Node elem) throws FullQueueException;
    int size();
    Node first();
}

