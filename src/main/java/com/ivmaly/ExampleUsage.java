package com.ivmaly;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ExampleUsage {
    public static void main(String[] args) {
        System.out.println("\nDemonstrating MyArrayList");
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        completeDemo(myArrayList);

        System.out.println("\nDemonstrating MyLinkedList");
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        completeDemo(myLinkedList);
    }

    private static void completeDemo(MyList<Integer> myList) {
        addDemo(myList);
        addAllDemo(myList);
        getDemo(myList);
        setDemo(myList);
        removeByIndexDemo(myList);
        removeByValueDemo(myList);
        staticSortDemo(myList);
        sortDemo(myList);
    }

    // Demonstrates the add method
    private static void addDemo(MyList<Integer> myList) {
        for (int i = 0; i < 10; i++) {
            myList.add(i);
        }
    }

    // Demonstrates the addAll method
    private static void addAllDemo(MyList<Integer> myList) {
        System.out.println("\nDemonstrating addAll method");
        List<Integer> list = Arrays.asList(1, 4, 10, 3, -1, 2, 10, 4, 10, 7, 0, 10);
        myList.addAll(list);
        System.out.println("List after adding another collection and list:");
        System.out.println(myList);
    }

    // Demonstrates the get method
    private static void getDemo(MyList<Integer> myList) {
        System.out.println("\nDemonstrating get method");
        System.out.println(myList);
        System.out.println("Getting the first element (element at index 0): " + myList.get(0));
    }

    // Demonstrates the set method
    private static void setDemo(MyList<Integer> myList) {
        System.out.println("\nDemonstrating set method");
        System.out.println(myList);
        myList.set(myList.getCapacity() - 1, 1000);
        System.out.println("Setting the last element value to 1000:");
        System.out.println(myList);
    }

    // Demonstrates the remove method by index
    private static void removeByIndexDemo(MyList<Integer> myList) {
        System.out.println("\nDemonstrating remove method by index");
        System.out.println(myList);
        int indexToRemove = 1;
        myList.remove(indexToRemove);
        System.out.println("Removing element at index " + indexToRemove + ":");
        System.out.println(myList);
    }

    // Demonstrates the remove method by value
    // Remove by value method removes the first occurrence of an element with the specified value
    // or does nothing if not found
    private static void removeByValueDemo(MyList<Integer> myList) {
        System.out.println("\nDemonstrating remove method by value");
        System.out.println(myList);
        Integer valueToRemove = 10;
        myList.remove(valueToRemove);
        System.out.println("Removed the first occurrence of element with value " + valueToRemove + ":");
        System.out.println(myList);
    }

    // Demonstrates the static sort method (defined in MyList interface)
    private static void staticSortDemo(MyList<Integer> myList) {
        System.out.println("\nDemonstrating static sort method (defined in MyList interface)");
        System.out.println(myList);
        MyList.sort(myList);
        System.out.println("List after sorting:");
        System.out.println(myList);
    }

    // Demonstrates the sort method (defined in the list class), which expects a Comparator
    private static void sortDemo(MyList<Integer> myList) {
        System.out.println("\nDemonstrating sort method (defined in the list class), which expects a Comparator");
        System.out.println(myList);
        myList.sort(Comparator.reverseOrder());
        System.out.println("List after sorting in reverse order:");
        System.out.println(myList);
    }

    private static String nullTets(String[] arr) {
        for (String s : arr) {
            System.out.println(s);
        }
        return " ";
    }
}
