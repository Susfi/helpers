package com.epam.prejap.ess.helpers.collectionsfill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * <p>Collections: fill, nCopies, addAll – and others how to quickly fill a collection?</p>
 *
 * Comparison of performance of different approaches to create new collection and fill it with elements
 * The test are done using list
 *
 * Result example
 * Test: list of 1000000 elements, average time in ms after 10 tries
 * Methode	time
 * clone 2
 * addALL 8
 * nCopies 11
 * addAll(NCopies) 12
 * Arrays.fill 16
 * List.fill 19
 * For loop 106
 * stream 91
 *
 * clone(): The fastest way to fill a Collection with elements is to clone existing one, however we have no control
 * over number of elements, nor what those elements are. We are also limited by what collections exist in the program.
 * clone() creates a shallow copy of Collection, so it does not creates copies of objects.
 * clone() is useful when we want to make changes to Collection (i.e. sort it) without affecting the original.
 *
 * addAll(): This method appends all of the elements in the specified Collection to the end of the list. This method
 * does not create copies of objects, only copies of references. Slightly slower than clone() but can be used to add
 * elements to existing collection.
 *
 * Collections.fill(): Replaces all of the elements of the specified list with the specified element. It does not
 * change the number of elements in the collection, so it cannot be used to initialize collection without fixed size.
 *
 * Collection.nCopies(): creates an immutable List containing the specified number of copies of the given object.
 * It can be used to initialize a newly created List, or to grow an existing List by using it with adAll() method.
 * It does not create copies of given object, only the copies of the references to a single object so it is memory
 * efficient
 *
 * Creating a list with for loop or stream are the slowest options, and they are memory inefficient as they create
 * multiple copies of given object.
 *
 * Conclusion: the best way to create a list with n elements, or append existing list by n elements, is to use method
 * nCopies() from Collections framework, unless desired effect is to have a list of copies of object.
 * @author Paweł Susfał
 */
class CollectionFillTimeTester {
    private static final int NUMBER_OF_ELEMENTS = 1_000_000;
    private static final int NUMBER_OF_TRIES = 10;

    public static void main(String[] args) {
        long startTime, endTime;
        long timeArraysFill = 0, timeNCopies = 0, timeStream = 0, timeForLoop = 0, timeListFill = 0,
                timeClone = 0, timeAddAll = 0, timeAddAllNCopies = 0;

        for(int i = 0; i < NUMBER_OF_TRIES; i++) {

            startTime = System.currentTimeMillis();
            ArrayList<Object> arrayListFromArraysFill = new ArrayList<>(generateListUsingArraysFill());
            endTime = System.currentTimeMillis();
            timeArraysFill += endTime - startTime;

            startTime = System.currentTimeMillis();
            ArrayList<Object> arrayListFromListFill = new ArrayList<>(generateListUsingFill());
            endTime = System.currentTimeMillis();
            timeListFill += endTime - startTime;

            startTime = System.currentTimeMillis();
            ArrayList<Object> arrayListFromNCopies = new ArrayList<>(generateListUsingCollectionsNCopies());
            endTime = System.currentTimeMillis();
            timeNCopies += endTime - startTime;


            startTime = System.currentTimeMillis();
            ArrayList<Object> arrayListFromStream = new ArrayList<>(generateListUsingStream());
            endTime = System.currentTimeMillis();
            timeStream += endTime - startTime;


            startTime = System.currentTimeMillis();
            ArrayList<Object> arrayListFromLoop = new ArrayList<>(generateListUsingLoop());
            endTime = System.currentTimeMillis();
            timeForLoop += endTime - startTime;

            startTime = System.currentTimeMillis();
            ArrayList<Object> arrayListFromClone = (ArrayList<Object>) arrayListFromArraysFill.clone();
            endTime = System.currentTimeMillis();
            timeClone += endTime - startTime;

            startTime = System.currentTimeMillis();
            ArrayList<Object> arrayListFromAddAll = new ArrayList<>();
            arrayListFromAddAll.addAll(arrayListFromArraysFill);
            endTime = System.currentTimeMillis();
            timeAddAll += endTime - startTime;

            startTime = System.currentTimeMillis();
            ArrayList<Object> arrayListFromNCopiesAddAll = new ArrayList<>();
            arrayListFromNCopiesAddAll.addAll(Collections.nCopies(NUMBER_OF_ELEMENTS, new Object()));
            endTime = System.currentTimeMillis();
            timeAddAllNCopies += endTime - startTime;
        }

        String resultMessage = "Test: list of " + NUMBER_OF_ELEMENTS +
                " elements, average time in ms after " + NUMBER_OF_TRIES + " tries" + "\n" +
                "Methode\ttime" + "\n" +
                "clone " + timeClone / NUMBER_OF_TRIES + "\n" +
                "addALL " + timeAddAll / NUMBER_OF_TRIES + "\n" +
                "nCopies " + timeNCopies / NUMBER_OF_TRIES + "\n" +
                "addAll(nCopies) " + timeAddAllNCopies / NUMBER_OF_TRIES + "\n" +
                "Arrays.fill " + timeArraysFill / NUMBER_OF_TRIES + "\n" +
                "List.fill " + timeListFill / NUMBER_OF_TRIES + "\n" +
                "for loop " + timeForLoop / NUMBER_OF_TRIES + "\n" +
                "stream " + timeStream / NUMBER_OF_TRIES + "\n";

        System.out.println(resultMessage);
    }

    private static List<Object> generateListUsingCollectionsNCopies(){
        return Collections.nCopies(NUMBER_OF_ELEMENTS, new Object());
    }

    private static List<Object> generateListUsingArraysFill() {
        Object[] data = new Object[NUMBER_OF_ELEMENTS];
        Arrays.fill(data,new Object());
        return Arrays.asList(data);
    }

    private static List<Object> generateListUsingFill() {
        List<Object> list = new ArrayList<>(Arrays.asList(new Object[NUMBER_OF_ELEMENTS]));
        Collections.fill(list, new Object());
        return list;
    }

    private static List<Object> generateListUsingStream(){
        return Stream.generate(String::new)
                .limit(NUMBER_OF_ELEMENTS)
                .map(s -> new Object())
                .collect(Collectors.toList());
    }

    private static List<Object> generateListUsingLoop(){
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.add(new Object());
        }

        return list;
    }
}
