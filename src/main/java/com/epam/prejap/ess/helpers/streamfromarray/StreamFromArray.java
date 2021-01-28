package com.epam.prejap.ess.helpers.streamfromarray;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Presents different methods of producing a stream out of an array
 * @author Magdalena Żaba
 */
class StreamFromArray {

    /**
     * Creates a stream out of array of objects using Arrays.stream
     *
     * @param someArray array of objects
     * @param <T>       type of array elements
     * @return Stream<T>  stream for array
     */
    static <T> Stream<T> createIntStreamOutOfArrayUsingArrays(T[] someArray) {
        return Arrays.stream(someArray);
    }

    /**
     * Creates a stream out of array of objects using Arrays.stream within a given range
     *
     * @param someArray  array of objects
     * @param lowerBound starting index of array inclusive
     * @param upperBound ending index of array exclusive
     * @param <T>        type of array elements
     * @return Stream<T>  stream for array within a given range
     * @throws ArrayIndexOutOfBoundsException when lowerBound is negative, upperBound is greater than array size or upperBound is less than lowerBound
     */
    static <T> Stream<T> createIntStreamOutOfArrayWithinGivenRangeUsingArrays(T[] someArray, int lowerBound, int upperBound) {
        return Arrays.stream(someArray, lowerBound, upperBound);
    }


    /**
     * Creates a stream out of array of objects using Stream.of
     * Note that under the hood it uses Arrays.stream
     *
     * @param someArray array of objects
     * @param <T>       type of array elements
     * @return Stream<T>  stream for array
     */
    static <T> Stream<T> createStreamOutOfArrayUsingStream(T[] someArray) {
        return Stream.of(someArray);
    }

    /**
     * Creates an arraylist of array and stream of this list
     *
     * @param someArray array of objects
     * @param <T>       type of array elements
     * @return Stream<T>  stream for array
     */
    static <T> Stream<T> createStreamOutOfArrayUsingCollection(T[] someArray) {
        return Arrays.asList(someArray).stream();
    }

    //below some specific methods for creating with arrays of primitive types as a source

    /**
     * Creates an IntStream out of array of int numbers using Arrays.stream
     *
     * @param someArray array of int numbers
     * @return IntStream for array
     */
    static IntStream createIntStreamOutOfArrayUsingArrays(int[] someArray) {
        return Arrays.stream(someArray);
    }

    /**
     * Creates an IntStream out of array of int numbers using Arrays.stream within a given range
     *
     * @param someArray  array of int numbers
     * @param lowerBound starting index of array inclusive
     * @param upperBound ending index of array exclusive
     * @return IntStream for array within a given range
     * @throws ArrayIndexOutOfBoundsException when lowerBound is negative, upperBound is greater than array size or upperBound is less than lowerBound
     */
    static IntStream createIntStreamOutOfArrayWithinGivenRangeUsingArrays(int[] someArray, int lowerBound, int upperBound) {
        return Arrays.stream(someArray, lowerBound, upperBound);
    }

    /**
     * Creates a LongStream out of array of long numbers using Arrays.stream
     *
     * @param someArray array of long numbers
     * @return LongStream for array
     */
    static LongStream createLongStreamOutOfArrayUsingArrays(long[] someArray) {
        return Arrays.stream(someArray);
    }

    /**
     * Creates a LongStream out of array of long numbers using Arrays.stream within a given range
     *
     * @param someArray  array of long numbers
     * @param lowerBound starting index of array inclusive
     * @param upperBound ending index of array exclusive
     * @return LongStream for array within a given range
     * @throws ArrayIndexOutOfBoundsException when lowerBound is negative, upperBound is greater than array size or upperBound is less than lowerBound
     */
    static LongStream createLongStreamOutOfArrayWithinGivenRangeUsingArrays(long[] someArray, int lowerBound, int upperBound) {
        return Arrays.stream(someArray, lowerBound, upperBound);
    }

    /**
     * Creates a DoubleStream out of array of double numbers using Arrays.stream
     *
     * @param someArray array of double numbers
     * @return DoubleStream for array
     */
    static DoubleStream createDoubleStreamOutOfArrayUsingArrays(double[] someArray) {
        return Arrays.stream(someArray);
    }

    /**
     * Creates a DoubleStream out of array of double numbers using Arrays.stream within a given range
     *
     * @param someArray  array of double numbers
     * @param lowerBound starting index of array inclusive
     * @param upperBound ending index of array exclusive
     * @return DoubleStream for array within a given range
     * @throws ArrayIndexOutOfBoundsException when lowerBound is negative, upperBound is greater than array size or upperBound is less than lowerBound
     */
    static DoubleStream createDoubleStreamOutOfArrayWithinGivenRangeUsingArrays(double[] someArray, int lowerBound, int upperBound) {
        return Arrays.stream(someArray, lowerBound, upperBound);
    }
}
