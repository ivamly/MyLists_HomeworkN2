package com.ivmaly;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
    private Object[] array;
    private int capacity = 10;
    private int currentSize = 0;

    public MyArrayList() {
        array = new Object[capacity];
    }

    public MyArrayList(MyList<? extends T> list) {
        this();
        addAll(list);
    }

    public MyArrayList(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

    public MyArrayList(T[] array) {
        this();
        addAll(array);
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        array[currentSize++] = element;
    }

    @Override
    public void add(int index, T element) {
        if (isNotValidIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }

        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, currentSize - index);
        array[index] = element;
        currentSize++;
    }

    @Override
    public void remove(int index) {
        if (isNotValidIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }

        System.arraycopy(array, index + 1, array, index, currentSize - index - 1);
        array[--currentSize] = null;
        shrinkIfNeeded();
    }

    @Override
    public void remove(T element) {
        if (element == null) {
            throw new NullPointerException("Element must be not null");
        }

        for (int i = 0; i < currentSize; i++) {
            if (element.equals(array[i])) {
                remove(i);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (isNotValidIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }

        return (T) array[index];
    }

    @Override
    public void set(int index, T element) {
        if (isNotValidIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }

        array[index] = element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sort(Comparator<? super T> comparator) {
        if (currentSize < 2) {
            return;
        }

        boolean wasSwap;

        do {
            wasSwap = false;
            for (int i = 0; i < currentSize - 1; i++) {
                if (comparator.compare((T) array[i], (T) array[i + 1]) > 0) {
                    T temp = (T) array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    wasSwap = true;
                }
            }
        } while (wasSwap);
    }

    @Override
    public int getCapacity() {
        return currentSize;
    }

    private void ensureCapacity() {
        if (currentSize >= capacity) {
            capacity *= 2;
            array = Arrays.copyOf(array, capacity);
        }
    }

    private void shrinkIfNeeded() {
        if (currentSize < capacity / 3 && capacity > 10) {
            capacity /= 2;
            array = Arrays.copyOf(array, capacity);
        }
    }

    private boolean isNotValidIndex(int index) {
        return index < 0 || index >= currentSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return capacity == that.capacity && currentSize == that.currentSize && Objects.deepEquals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), capacity, currentSize);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < currentSize; i++) {
            builder.append(array[i]);
            if (i < currentSize - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
