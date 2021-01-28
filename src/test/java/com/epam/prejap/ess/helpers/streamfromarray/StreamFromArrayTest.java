package com.epam.prejap.ess.helpers.streamfromarray;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class StreamFromArrayTest {

    private final static String[] SAMPLE_STRING_ARRAY = new String[]{"alfa", "beta", "gamma", "delta"};
    private final static int[] SAMPLE_INT_ARRAY = new int[]{1, 2, 3, 4};
    private final static long[] SAMPLE_LONG_ARRAY = new long[]{1000L, 2000L, 3000L, 4000L};
    private final static double[] SAMPLE_DOUBLE_ARRAY = new double[]{1.1, 2.2, 3.3, 4.4};
    private final static List<String> OUTPUT_STRING_LIST_FROM_STREAM = List.of("alfa", "beta", "gamma", "delta");

    @Test
    public void shouldProduceStreamOutOfArrayOfObjectsUsingArrays() {
        assertEquals(StreamFromArray.createIntStreamOutOfArrayUsingArrays(SAMPLE_STRING_ARRAY).collect(Collectors.toList()), OUTPUT_STRING_LIST_FROM_STREAM);
    }

    @Test
    public void shouldProduceStreamOutOfArrayOfObjectsWithinGivenRangeUsingArrays() {
        assertEquals(StreamFromArray.createIntStreamOutOfArrayWithinGivenRangeUsingArrays(SAMPLE_STRING_ARRAY, 1, 3).collect(Collectors.toList()), List.of("beta", "gamma"));
    }

    @Test
    public void shouldProduceStreamOutOfArrayUsingStream() {
        assertEquals(StreamFromArray.createStreamOutOfArrayUsingStream(SAMPLE_STRING_ARRAY).collect(Collectors.toList()), OUTPUT_STRING_LIST_FROM_STREAM);
    }

    @Test
    public void shouldProduceStreamOutOfArrayUsingCollection() {
        assertEquals(StreamFromArray.createStreamOutOfArrayUsingCollection(SAMPLE_STRING_ARRAY).collect(Collectors.toList()), OUTPUT_STRING_LIST_FROM_STREAM);
    }

    @DataProvider
    Object[][] illegalRange(){
        return new Object[][] {{Integer.valueOf(-1),Integer.valueOf(2)},
                {Integer.valueOf(3),Integer.valueOf(2)},
                {Integer.valueOf(2),Integer.valueOf(5)}};
    }

    @Test(dataProvider = "illegalRange", expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowWhenIllegalRangeWithArrayOfObjects(int lowerBound, int upperBound) {
        StreamFromArray.createIntStreamOutOfArrayWithinGivenRangeUsingArrays(SAMPLE_STRING_ARRAY, lowerBound, upperBound);
    }

    @Test
    public void shouldProduceStreamOutOfArrayOfIntUsingArrays() {
        assertEquals(StreamFromArray.createIntStreamOutOfArrayUsingArrays(SAMPLE_INT_ARRAY).boxed().collect(Collectors.toList()), List.of(1, 2, 3, 4));
    }

    @Test
    public void shouldProduceStreamOutOfArrayOfIntWithinGivenRangeUsingArrays() {
        assertEquals(StreamFromArray.createIntStreamOutOfArrayWithinGivenRangeUsingArrays(SAMPLE_INT_ARRAY, 1, 3).boxed().collect(Collectors.toList()), List.of(2,3));
    }

    @Test(dataProvider = "illegalRange", expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowWhenIllegalRangeWithArrayOfInt(int lowerBound, int upperBound) {
        StreamFromArray.createIntStreamOutOfArrayWithinGivenRangeUsingArrays(SAMPLE_INT_ARRAY, lowerBound, upperBound);
    }

    @Test
    public void shouldProduceStreamOutOfArrayOfLongUsingArrays() {
        assertEquals(StreamFromArray.createLongStreamOutOfArrayUsingArrays(SAMPLE_LONG_ARRAY).boxed().collect(Collectors.toList()), List.of(1000L, 2000L, 3000L, 4000L));
    }

    @Test
    public void shouldProduceStreamOutOfArrayOfLongWithinGivenRangeUsingArrays() {
        assertEquals(StreamFromArray.createLongStreamOutOfArrayWithinGivenRangeUsingArrays(SAMPLE_LONG_ARRAY, 1, 3).boxed().collect(Collectors.toList()), List.of(2000L,3000L));
    }

    @Test(dataProvider = "illegalRange", expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowWhenIllegalRangeWithArrayOfLong(int lowerBound, int upperBound) {
        StreamFromArray.createLongStreamOutOfArrayWithinGivenRangeUsingArrays(SAMPLE_LONG_ARRAY, lowerBound, upperBound);
    }

    @Test
    public void shouldProduceStreamOutOfArrayOfDoubleUsingArrays() {
        assertEquals(StreamFromArray.createDoubleStreamOutOfArrayUsingArrays(SAMPLE_DOUBLE_ARRAY).boxed().collect(Collectors.toList()), List.of(1.1, 2.2, 3.3, 4.4));
    }

    @Test
    public void shouldProduceStreamOutOfArrayOfDoubleWithinGivenRangeUsingArrays() {
        assertEquals(StreamFromArray.createDoubleStreamOutOfArrayWithinGivenRangeUsingArrays(SAMPLE_DOUBLE_ARRAY, 1, 3).boxed().collect(Collectors.toList()), List.of(2.2,3.3));
    }

    @Test(dataProvider = "illegalRange", expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowWhenIllegalRangeWithArrayOfDouble(int lowerBound, int upperBound) {
        StreamFromArray.createDoubleStreamOutOfArrayWithinGivenRangeUsingArrays(SAMPLE_DOUBLE_ARRAY, lowerBound, upperBound);
    }
}
