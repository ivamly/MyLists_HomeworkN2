package com.ivmaly;

import java.util.Collection;
import java.util.Comparator;

public interface MyList<T> {
    void add(T o);

    void add(int index, T o);

    default void addAll(Collection<? extends T> c) {
        if (c != null) {
            for (T o : c) {
                add(o);
            }
        }
    }

    default void addAll(MyList<? extends T> c) {
        if (c != null) {
            for (int i = 0; i < c.getCapacity(); i++) {
                add(c.get(i));
            }
        }
    }

    default void addAll(T[] array) {
        if (array != null) {
            for (T o : array) {
                add(o);
            }
        }
    }

    void remove(T element);

    void remove(int index);

    T get(int index);

    void set(int index, T o);

    int getCapacity();

    void sort(Comparator<? super T> c);

    static <E extends Comparable<E>> void sort(MyList<E> myList) {
        if (myList.getCapacity() < 2)
            return;

        boolean wasSwap;

        do {
            wasSwap = false;
            for (int i = 0; i < myList.getCapacity() - 1; i++) {
                if (myList.get(i).compareTo(myList.get(i + 1)) > 0) {
                    E temp = myList.get(i);
                    myList.set(i, myList.get(i + 1));
                    myList.set(i + 1, temp);
                    wasSwap = true;
                }
            }
        } while (wasSwap);
    }

}