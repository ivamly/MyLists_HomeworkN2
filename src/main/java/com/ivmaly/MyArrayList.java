package com.ivmaly;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
    private T[] array;
    private int size = 10;
    private int currentSize = 0;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        array = (T[]) new Object[size];
    }

    public MyArrayList(MyList<? extends T> list) {
        this();
        for (int i = 0; i < list.getSize(); i++) {
            add(list.get(i));
        }
    }

    public MyArrayList(Collection<? extends T> collection) {
        this();
        for (T element : collection) {
            add(element);
        }
    }

    public MyArrayList(T[] array) {
        this();
        for (T element : array) {
            add(element);
        }
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        array[currentSize++] = element;
    }

    @Override
    public void add(int index, T element) {
        if (isValidIndex(index)) {
            ensureCapacity();
            for (int i = currentSize; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = element;
            currentSize++;
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
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
            for (int i = index; i < currentSize - 1; i++) {
                array[i] = array[i + 1];
            }
            array[--currentSize] = null;
            shrinkIfNeeded();
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }
    }

    @Override
    public void remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (element.equals(array[i])) {
                remove(i);
                return;
            }
        }
    }

    @Override
    public T get(int index) {
        if (isValidIndex(index)) {
            return array[index];
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }
    }

    @Override
    public void set(int index, T element) {
        if (isValidIndex(index)) {
            array[index] = element;
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if (currentSize < 2) {
            return;
        }

        boolean wasSwap;

        do {
            wasSwap = false;
            for (int i = 0; i < currentSize - 1; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    T temp = array[i];
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

    // Метод сделан публичным, для того чтобы протестировать увеличение и уменьшение размера массива
    public int getAvailableSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (currentSize >= size) {
            size *= 2;
            T[] newArray = (T[]) new Object[size];
            myArrayCopy(array, 0, newArray, 0, currentSize);
            array = newArray;
        }
    }

    @SuppressWarnings("unchecked")
    private void shrinkIfNeeded() {
        if (currentSize < size / 3 && size > 10) {
            size /= 2;
            T[] newArray = (T[]) new Object[size];
            myArrayCopy(array, 0, newArray, 0, currentSize);
            array = newArray;
        }
    }

    private void myArrayCopy(T[] src, int srcPos, T[] dest, int destPos, int length) {
        for (int i = 0; i < length; i++) {
            dest[destPos + i] = src[srcPos + i];
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < currentSize;
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
