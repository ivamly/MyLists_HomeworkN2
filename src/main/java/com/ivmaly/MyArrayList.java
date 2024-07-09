package com.ivmaly;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
    private Object[] array;
    private int size = 10;
    private int currentSize = 0;

    public MyArrayList() {
        array = new Object[size];
    }

    public MyArrayList(MyList<? extends T> list) {
        this();
        if (list != null) {
            for (int i = 0; i < list.getSize(); i++) {
                add(list.get(i));
            }
        }
    }

    public MyArrayList(Collection<? extends T> collection) {
        this();
        if (collection != null) {
            for (T element : collection) {
                add(element);
            }
        }
    }

    public MyArrayList(T[] array) {
        this();
        if (array != null) {
            for (T element : array) {
                add(element);
            }
        }
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
    public void addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Collection must be not null");
        }

        ensureCapacity(currentSize + c.size());
        for (T element : c) {
            add(element);
        }
    }

    @Override
    public void addAll(MyList<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Collection must be not null");
        }

        ensureCapacity(currentSize + c.getSize());
        for (int i = 0; i < c.getSize(); i++) {
            add(c.get(i));
        }
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
    public int getSize() {
        return currentSize;
    }

    private void ensureCapacity() {
        if (currentSize >= size) {
            size *= 2;
            array = Arrays.copyOf(array, size);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > size) {
            size = Math.max(size * 2, minCapacity);
            array = Arrays.copyOf(array, size);
        }
    }

    private void shrinkIfNeeded() {
        if (currentSize < size / 3 && size > 10) {
            size /= 2;
            array = Arrays.copyOf(array, size);
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
        return size == that.size && currentSize == that.currentSize && Objects.deepEquals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size, currentSize);
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
