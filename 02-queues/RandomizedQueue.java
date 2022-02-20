/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class Node {
        private Item data;
        private Node next;
    }

    private class RdQueueIterator implements Iterator<Item> {
        private Node node = head;

        public boolean hasNext() {
            return (node.next != null);
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
    private Node head;

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
        int randomNumber = StdRandom.uniform(1, size);
        Node current = head;
        for (int i = 0; i < randomNumber - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        current.next.next = null;
        size--;
        if (current.next.data == null) {
            throw new java.util.NoSuchElementException();
        }
        return current.next.data;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int randomNumber = StdRandom.uniform(1, size);
        Node current = head;
        for (int i = 0; i < randomNumber; i++) {
            current = current.next;
        }
        if (current.data == null) {
            throw new java.util.NoSuchElementException();
        }
        return current.data;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RdQueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(rq.dequeue());
            else
                rq.enqueue(s);
        }
    }
}
