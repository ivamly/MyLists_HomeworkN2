package com.ivmaly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyArrayListTest {

    private MyArrayList<Integer> myArrayList;

    @BeforeEach
    void setUp() {
        myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
    }

    @Test
    void testConstructorWithMyList() {
        MyArrayList<Integer> list = new MyArrayList<>(myArrayList);
        assertEquals(3, list.getSize());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testConstructorWithCollection() {
        List<Integer> collection = Arrays.asList(4, 5, 6);
        MyArrayList<Integer> list = new MyArrayList<>(collection);
        assertEquals(3, list.getSize());
        assertEquals(4, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(6, list.get(2));
    }

    @Test
    void testConstructorWithArray() {
        String[] array = {"1", "2", "3", "4", "5"};
        MyArrayList<String> list = new MyArrayList<>(array);
        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        assertEquals(list, list2);
    }

    @Test
    void testAdd() {
        myArrayList.add(4);
        assertEquals(4, myArrayList.get(3));
    }

    @Test
    void testAddAtIndex() {
        myArrayList.add(1, 10);
        assertEquals(10, myArrayList.get(1));
        assertEquals(2, myArrayList.get(2));
    }

    @Test
    void testAddAllCollection() {
        List<Integer> collection = new ArrayList<>();
        collection.add(4);
        collection.add(5);
        collection.add(6);
        myArrayList.addAll(collection);
        assertEquals(6, myArrayList.getSize());
        assertEquals(6, myArrayList.get(5));
    }

    @Test
    void testAddAllMyCollection() {
        MyArrayList<Integer> myCollection = new MyArrayList<>(myArrayList);
        myArrayList.addAll(myCollection);
        assertEquals(6, myArrayList.getSize());
        assertEquals(3, myArrayList.get(5));
    }

    @Test
    void testRemoveByIndex() {
        myArrayList.remove(1);
        assertEquals(3, myArrayList.get(1));
    }

    @Test
    void testRemoveByElement() {
        myArrayList.remove((Integer) 2);
        assertEquals(3, myArrayList.get(1));
    }

    @Test
    void testGet() {
        assertEquals(2, myArrayList.get(1));
    }

    @Test
    void testEnsureCapacity() {
        for (int i = 0; i < 10; i++) {
            myArrayList.add(i);
        }
        assertEquals(20, myArrayList.getAvailableSize());
    }

    @Test
    void testShrinkIfNeeded() {
        for (int i = 0; i < 30; i++) {
            myArrayList.add(i);
        }
        assertEquals(40, myArrayList.getAvailableSize());
        for (int i = 0; i < 29; i++) {
            myArrayList.remove(0);
        }
        assertEquals(10, myArrayList.getAvailableSize());
    }

    @Test
    void testComarableSort() {
        myArrayList.add(-20);
        myArrayList.add(500);
        myArrayList.add(0);
        MyList.sort(myArrayList);
        MyArrayList<Integer> expected = new MyArrayList<>();
        expected.add(-20);
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(500);
        assertEquals(expected, myArrayList);
    }

    @Test
    void testComparatorSort() {
        myArrayList.add(-10);
        myArrayList.add(100);
        myArrayList.add(0);
        myArrayList.sort(Comparator.reverseOrder());
        MyArrayList<Integer> expected = new MyArrayList<>();
        expected.add(100);
        expected.add(3);
        expected.add(2);
        expected.add(1);
        expected.add(0);
        expected.add(-10);
        assertEquals(expected, myArrayList);
    }
}

