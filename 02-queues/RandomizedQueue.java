/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class Node {
        private Item data;
        private Node next;
    }

    private class RdQueueIterator implements Iterator<Item> {
        private Node node = head.next;

        public boolean hasNext() {
            return (node != null);
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = node.data;
            node = node.next;
            return item;
        }
    }

    private int size;
    private final Node head;

    // construct an empty randomized queue
    public RandomizedQueue() {
        head = new Node();
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        Node newNode = new Node();
        current.next = newNode;
        newNode.data = item;
        newNode.next = null;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        int randomNumber = StdRandom.uniform(size);
        // StdOut.print("random number is: " + randomNumber + "\n");
        Node current = head;
        for (int i = 0; i < randomNumber; i++) {
            current = current.next;
            // StdOut.println("next data: " + current.data);
        }
        Item retData = current.next.data;
        // StdOut.println("retData: " + retData);
        current.next = current.next.next == null ? null : current.next.next;
        // current.next.next = null;
        size--;
        if (retData == null) {
            throw new NoSuchElementException();
        }
        return retData;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int randomNumber = StdRandom.uniform(size);
        // StdOut.print("random number is: " + randomNumber + "\n");
        Node current = head.next;
        for (int i = 0; i < randomNumber; i++) {
            current = current.next;
        }
        if (current.data == null) {
            throw new NoSuchElementException();
        }
        return current.data;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RdQueueIterator();
    }

    public static void main(String[] args) {
        // RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        // rq.enqueue(1);
        // rq.enqueue(2);
        // rq.enqueue(3);
        // rq.enqueue(4);
        // rq.enqueue(5);
        // StdOut.print("size: " + rq.size() + "\n");
        // // StdOut.print("sample: " + rq.sample() + "\n");
        // StdOut.print("dequeue [1]: " + rq.dequeue() + "\n");
        // StdOut.print("dequeue [2]: " + rq.dequeue() + "\n");
        // StdOut.print("dequeue [3]: " + rq.dequeue() + "\n");
        // StdOut.print("dequeue [4]: " + rq.dequeue() + "\n");
        // StdOut.print("dequeue [5]: " + rq.dequeue() + "\n");
    }
}
