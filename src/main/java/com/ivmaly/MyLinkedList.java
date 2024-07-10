package com.ivmaly;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int capacity;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        capacity = 0;
    }

    public MyLinkedList(MyList<T> myList) {
        this();
        addAll(myList);
    }

    public MyLinkedList(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

    public MyLinkedList(T[] array) {
        this();
        addAll(array);
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element, null, tail);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        capacity++;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > capacity) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + capacity);
        }

        if (index == capacity) {
            add(element);
            return;
        }

        Node<T> nextNode = getNode(index);
        Node<T> prevNode = nextNode.prev;
        Node<T> newNode = new Node<>(element, nextNode, prevNode);
        nextNode.prev = newNode;
        if (prevNode == null) {
            head = newNode;
        } else {
            prevNode.next = newNode;
        }
        capacity++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + capacity);
        }

        Node<T> nodeToRemove = getNode(index);
        removeNode(nodeToRemove);
    }

    @Override
    public void remove(T element) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.data, element)) {
                removeNode(current);
                return;
            }
            current = current.next;
        }
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public void set(int index, T element) {
        Node<T> node = getNode(index);
        node.data = element;
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if (capacity < 2) {
            return;
        }

        boolean wasSwap;
        do {
            wasSwap = false;
            Node<T> current = head;
            while (current != null && current.next != null) {
                if (comparator.compare(current.data, current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    wasSwap = true;
                }
                current = current.next;
            }
        } while (wasSwap);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + capacity);
        }

        Node<T> current;
        if (index < capacity / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = capacity - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void removeNode(Node<T> node) {
        Node<T> prevNode = node.prev;
        Node<T> nextNode = node.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            tail = prevNode;
        }

        capacity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        if (capacity != that.capacity) return false;

        Node<T> currentThis = head;
        Node<?> currentThat = that.head;

        while (currentThis != null) {
            if (!Objects.equals(currentThis.data, currentThat.data)) {
                return false;
            }
            currentThis = currentThis.next;
            currentThat = currentThat.next;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity);
        Node<T> current = head;
        while (current != null) {
            result = 31 * result + Objects.hashCode(current.data);
            current = current.next;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<T> current = head;
        while (current != null) {
            builder.append(current.data);
            if (current.next != null) {
                builder.append(", ");
            }
            current = current.next;
        }
        builder.append("]");
        return builder.toString();
    }
}
