package com.ivmaly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<Integer> myLinkedList;

    @BeforeEach
    void setUp() {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
    }

    @Test
    void testConstructorWithMyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>(myLinkedList);

        // Проверяем, что элементы были корректно скопированы
        assertEquals(3, list.getCapacity());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testConstructorWithCollection() {
        Collection<Integer> collection = Arrays.asList(4, 5, 6);
        MyLinkedList<Integer> list = new MyLinkedList<>(collection);

        // Проверяем, что элементы были корректно добавлены
        assertEquals(3, list.getCapacity());
        assertEquals(4, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(6, list.get(2));
    }

    @Test
    void testAdd() {
        myLinkedList.add(4);
        assertEquals(4, myLinkedList.get(myLinkedList.getCapacity() - 1));
    }

    @Test
    void testAddAtIndex() {
        myLinkedList.add(1, 10);
        assertEquals(10, myLinkedList.get(1));
        assertEquals(2, myLinkedList.get(2));
    }

    @Test
    void testAddAllCollection() {
        List<Integer> collection = new ArrayList<>();
        collection.add(4);
        collection.add(5);
        collection.add(6);
        myLinkedList.addAll(collection);
        assertEquals(6, myLinkedList.getCapacity());
        assertEquals(6, myLinkedList.get(5));
    }

    @Test
    void testAddAllMyCollection() {
        MyArrayList<Integer> myCollection = new MyArrayList<>();
        myCollection.add(1);
        myCollection.add(2);
        myCollection.add(3);
        myLinkedList.addAll(myCollection);
        assertEquals(6, myLinkedList.getCapacity());
        assertEquals(3, myLinkedList.get(5));
    }

    @Test
    void testConstructorWithArray() {
        String[] array = {"1", "2", "3", "4", "5"};
        MyLinkedList<String> list = new MyLinkedList<>(array);
        MyLinkedList<String> list2 = new MyLinkedList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        assertEquals(list, list2);
    }

    @Test
    void testRemoveByIndex() {
        myLinkedList.remove(1);
        assertEquals(3, myLinkedList.get(1));
    }

    @Test
    void testRemoveByElement() {
        myLinkedList.remove((Integer) 2);
        assertEquals(3, myLinkedList.get(1));
    }

    @Test
    void testSize() {
        assertEquals(3, myLinkedList.getCapacity());
        myLinkedList.add(4);
        assertEquals(4, myLinkedList.getCapacity());
        myLinkedList.remove(0);
        assertEquals(3, myLinkedList.getCapacity());
    }

    @Test
    void testComarableSort() {
        myLinkedList.add(0);
        myLinkedList.add(-1);
        myLinkedList.add(15);
        MyList.sort(myLinkedList);
        MyLinkedList<Integer> expected = new MyLinkedList<>();
        expected.add(-1);
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(15);
        assertEquals(expected, myLinkedList);
    }

    @Test
    void testComparatorSort() {
        myLinkedList.add(0);
        myLinkedList.add(-5);
        myLinkedList.add(10);
        myLinkedList.sort(Comparator.reverseOrder());
        MyLinkedList<Integer> expected = new MyLinkedList<>();
        expected.add(10);
        expected.add(3);
        expected.add(2);
        expected.add(1);
        expected.add(0);
        expected.add(-5);
        assertEquals(expected, myLinkedList);
    }
}
