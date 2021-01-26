package com.epam.prejap.ess.helpers.collectionsfill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionFillTimeTester {
    private static final int NUMBER_OF_ELEMENTS = 1_000_000;


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
