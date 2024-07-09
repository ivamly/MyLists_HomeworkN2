package com.ivmaly;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

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
        size = 0;
    }

    public MyLinkedList(MyList<T> myList) {
        for (int i = 0; i < myList.getSize(); i++) {
            add(myList.get(i));
        }
    }

    public MyLinkedList(Collection<? extends T> collection) {
        for (T element : collection) {
            add(element);
        }
    }

    public MyLinkedList(T[] array) {
        this();
        for (T element : array) {
            add(element);
        }
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
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (isValidIndex(index)) {
            if (index == size) {
                add(element);
                return;
            }
            if (index == 0) {
                Node<T> newNode = new Node<>(element, head, null);
                if (head == null) {
                    tail = newNode;
                } else {
                    head.prev = newNode;
                }
                head = newNode;
                size++;
            } else {
                Node<T> nextNode = getNode(index);
                Node<T> prevNode = nextNode.prev;
                Node<T> newNode = new Node<>(element, nextNode, prevNode);
                nextNode.prev = newNode;
                if (prevNode == null) {
                    head = newNode;
                } else {
                    prevNode.next = newNode;
                }
                size++;
            }
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
    }

    @Override
    public void addAll(MyList<? extends T> c) {
        for (int i = 0; i < c.getSize(); i++) {
            add(c.get(i));
        }
    }

    @Override
    public void remove(int index) {
        if (isValidIndex(index)) {
            Node<T> nodeToRemove = getNode(index);
            removeNode(nodeToRemove);
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void remove(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
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
        if (size < 2) {
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
    public int getSize() {
        return size;
    }

    private Node<T> getNode(int index) {
        if (isValidIndex(index)) {
            Node<T> current;
            if (index < size / 2) {
                current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
            } else {
                current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
            }
            return current;
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
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

        size--;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        if (size != that.size) return false;

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
        int result = Objects.hash(size);
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
        for (int i = 0; i < size; i++) {
            builder.append(get(i));
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
