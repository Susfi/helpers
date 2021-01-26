package com.epam.prejap.ess.helpers.collectionsfill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionFillTimeTester {
    private static final int NUMBER_OF_ELEMENTS = 1_000_000;
    private static final int NUMBER_OF_TRIES = 1;

    public static void main(String[] args) {
        long startTime, endTime;
        long timeArraysFill = 0, timeNCopies = 0, timeStream = 0, timeForLoop = 0, timeListFill = 0;

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
        }

        String resultMessage = "Test: list of " + NUMBER_OF_ELEMENTS +
                " elements, average time in ms after " + NUMBER_OF_TRIES + " tries" + "\n" +
                "Methode\ttime" + "\n" +
                "NCopies " + timeNCopies / NUMBER_OF_TRIES + "\n" +
                "Arrays.fill " + timeArraysFill / NUMBER_OF_TRIES + "\n" +
                "List.fill " + timeListFill / NUMBER_OF_TRIES + "\n" +
                "For loop " + timeForLoop / NUMBER_OF_TRIES + "\n" +
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
/*
result example
Test: list of 1000000 elements, average time in ms after 1 tries
Methode	time
NCopies 15
Arrays.fill 18
List.fill 22
For loop 87
stream 106
 */