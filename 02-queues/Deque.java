/* *****************************************************************************
 *  Name: Chen Zhou
 *  Date: 2022-02-16
 *  Description: My implementation of Deque
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private final Node first;
    private final Node last;

    private class Node {
        private Item data;
        private Node prev;
        private Node next;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node node = first.next;

        public boolean hasNext() {
            return (node != last);
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

    // construct an empty deque
    public Deque() {
        first = new Node();
        last = new Node();
        first.prev = null;
        first.next = last;
        last.prev = first;
        last.next = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            // throw exception
            throw new IllegalArgumentException();
        }
        Node node = new Node();
        node.data = item;
        node.prev = first;
        node.next = first.next;
        first.next.prev = node;
        first.next = node;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            // throw exception
            throw new IllegalArgumentException();
        }
        Node node = new Node();
        node.data = item;
        node.next = last;
        node.prev = last.prev;
        last.prev.next = node;
        last.prev = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Node delNode = first.next;
        if (delNode.data == null) {
            throw new NoSuchElementException();
        }
        first.next = delNode.next;
        delNode.next.prev = first;
        delNode.next = null;
        delNode.prev = null;
        size--;
        return delNode.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Node delNode = last.prev;
        if (delNode.data == null) {
            throw new NoSuchElementException();
        }
        last.prev = delNode.prev;
        delNode.prev.next = last;
        delNode.prev = null;
        delNode.next = null;
        size--;
        return delNode.data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        // StdOut.print("main \n");
        // Deque<Integer> deque = new Deque<>();
        // deque.addFirst(1);
        // deque.addFirst(2);
        // deque.addFirst(3);
        // deque.addFirst(4);
        // for (Integer i : deque) {
        //     StdOut.print(i);
        // }
    }

}
