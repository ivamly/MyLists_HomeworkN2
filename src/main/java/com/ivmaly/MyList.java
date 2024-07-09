package com.ivmaly;

import java.util.Collection;
import java.util.Comparator;

public interface MyList<T> {
    void add(T o);

    void add(int index, T o);

    void addAll(Collection<? extends T> c);

    void addAll(MyList<? extends T> c);

    void remove(T element);

    void remove(int index);

    T get(int index);

    void set(int index, T o);

    int getSize();

    void sort(Comparator<? super T> c);

    static <E extends Comparable<E>> void sort(MyList<E> myList) {
        if (myList.getSize() < 2)
            return;

        boolean wasSwap;

        do {
            wasSwap = false;
            for (int i = 0; i < myList.getSize() - 1; i++) {
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