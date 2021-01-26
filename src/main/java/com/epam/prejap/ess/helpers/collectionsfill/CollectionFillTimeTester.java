package com.epam.prejap.ess.helpers.collectionsfill;

import java.util.ArrayList;
import java.util.List;

public class CollectionFillTimeTester {
    private static final int NUMBER_OF_ELEMENTS = 1_000_000;


    private static List<Object> generateListUsingLoop(){
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.add(new Object());
        }

        return list;
    }
}
