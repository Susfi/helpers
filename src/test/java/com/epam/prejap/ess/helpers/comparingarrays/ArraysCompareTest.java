package com.epam.prejap.ess.helpers.comparingarrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class ArraysCompareTest {

    //given
    @Test(dataProvider = "primitives")
    public void testPrimitiveArraysComparison(byte[] first, byte[] second, int expected) {
        //when
        int actual = Arrays.compare(first, second);

        //then
        assertEquals(actual, expected);
    }

    //given
    @Test(dataProvider = "TrainObjects")
    public void testObjectArraysComparison(Train[] first, Train[] second, int expected) {
        //when
        int actual = Arrays.compare(first, second);

        //then
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
        int[] first = {1, 2, 3};
        int[] second = {0, 1, 1, 9, 5, 4};

        Arrays.compare(first, 3, 2, second, 1, 2);
    }

    @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowArrayIndexOutOfBoundsException() {
        int[] first = {1, 2, 3};
        int[] second = {0, 1, 1, 9, 5, 4};

        Arrays.compare(first, 1, 5, second, 1, 2);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowArrayNullPointerException() {
        int[] first = null;
        int[] second = {0, 1, 1, 9, 5, 4};

        Arrays.compare(first, 1, 5, second, 1, 2);
    }

    @DataProvider
    public Object[][] primitives() {
        return new Object[][]{
                {new byte[]{'a', 'b', 'c'}, new byte[]{'b', 'e', 'f'}, -1},
                {new byte[]{'i', 'k', 'a'}, new byte[]{'h', 'x', 'w'}, 1},
                {new byte[]{'p', 'a', 'y'}, new byte[]{'p', 'a', 'y'}, 0}
        };
    }

    @DataProvider
    public Object[][] TrainObjects() {
        Train[] arrOne = {new Train(1), new Train(50), new Train(7)};
        Train[] arrTwo = {new Train(8), new Train(9), new Train(12)};
        Train[] arrThree = {new Train(85), new Train(63), new Train(7)};
        Train[] arrFour = {new Train(2), new Train(40), new Train(18)};
        Train[] arrFive = {new Train(69), new Train(30), new Train(57)};
        Train[] arrSix = {new Train(4), new Train(3), new Train(2)};
        return new Object[][]{
                {arrOne, arrTwo, -1},
                {arrThree, arrFour, 1},
                {arrFive, arrSix, 1}
        };
    }
}
