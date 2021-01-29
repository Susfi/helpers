package com.epam.prejap.ess.helpers.comparingarrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Presentation of comparing arrays using helper class method
 * {@link Arrays#compare(Comparable[], Comparable[])}.
 *
 * @author Łukasz Prokop
 */
final class ArraysCompare {

    public static void main(String[] args) {
        comparingPrimitiveArrays();
        comparingPrimitiveArraysOverSpecifiedRanges();
        comparingNonPrimitiveArrays();
        comparingNonPrimitiveArraysUsingSpecifiedComparator();
        comparingNonPrimitiveArraysOverSpecifiedRanges();
    }

    /**
     * Comparing first with second primitive type arrays lexicographically.
     * <p>@implNote  Comparing subsequent indexes using Arrays.mismatch().</p>
     * <p>Return:<br>
     * - less than 0 when first found element is smaller;<br>
     * - greater than 0 when first found element is grater;<br>
     * - 0 when there is no mismatch.</p>
     *
     * @see Arrays#compare(int[], int[])
     */
    private static void comparingPrimitiveArrays() {
        int[] first = {1, 0, 0, 0};
        int[] second = {0, 1, 1, 9, 5, 4};

        System.out.format("Result of the comparison two primitive type arrays when first one is lexicographically grater: %d\n",
                Arrays.compare(first, second));
    }

    /**
     * Comparing first with second primitive type arrays lexicographically over the specified ranges.
     * <p>@implNote  Comparing subsequent indexes using Arrays.mismatch().</p>
     * <p>Return:<br>
     * - less than 0 when first found element is smaller;<br>
     * - greater than 0 when first found element is grater;<br>
     * - 0 when there is no mismatch.</p>
     * <p>Throws:<br>
     * - <b>IllegalArgumentException</b> - if aFromIndex > aToIndex or if bFromIndex > bToIndex;<br>
     * - <b>ArrayIndexOutOfBoundsException</b> - if aFromIndex < 0 or aToIndex > a.length or if bFromIndex < 0 or bToIndex > b.length;<br>
     * - <b>NullPointerException</b> - if either array is null.</p>
     *
     * @see Arrays#compare(int[], int[])
     */
    private static void comparingPrimitiveArraysOverSpecifiedRanges() {
        int[] first = {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0,};
        int[] second = {0, 1, 1, 9, 5, 4, 0, 1, 1, 9, 5, 4, 0, 1, 1, 9, 5, 4, 0, 1, 1, 9, 5, 4,};

        System.out.format("Result of the comparison two primitive type arrays between given indexes when first one is lexicographically less: %d\n",
                Arrays.compare(first, 5, 10, second, 5, 10));
    }

    /**
     * Comparing first with second non-primitive type arrays lexicographically.
     * <p>@implNote  Comparing subsequent indexes using Arrays.mismatch().</p>
     * <p>Return:<br>
     * - less than 0 when first found element is smaller;<br>
     * - greater than 0 when first found element is grater;<br>
     * - 0 when there is no mismatch.</p>
     * <p>Throws:<br>
     * - <b>NullPointerException</b> - if the comparator is null.</p>
     *
     * @see Arrays#compare(int[], int[])
     */
    private static void comparingNonPrimitiveArrays() {
        Train[] firstObj = {new Train(1),
                new Train(4),
                new Train(2),
                new Train(3)
        };
        Train[] secondObj = {new Train(0),
                new Train(5),
                new Train(6),
                new Train(7)
        };

        System.out.format("Result of the comparison two non-primitive type arrays by id when first" +
                        " one is lexicographically grater: %d\n",
                Arrays.compare(firstObj, secondObj));
    }

    /**
     * Comparing first with second non-primitive type arrays lexicographically using a specified comparator.
     * <p>@implNote  Comparing subsequent indexes using Arrays.mismatch().</p>
     * <p>Return:<br>
     * - less than 0 when first found element is smaller;<br>
     * - greater than 0 when first found element is grater;<br>
     * - 0 when there is no mismatch.</p>
     * <p>Throws:<br>
     * - <b>NullPointerException</b> - if the comparator is null.</p>
     *
     * @see Arrays#compare(int[], int[])
     */
    private static void comparingNonPrimitiveArraysUsingSpecifiedComparator() {
        Train[] firstObj = {new Train(1),
                new Train(4),
                new Train(2),
                new Train(3)
        };
        Train[] secondObj = {new Train(0),
                new Train(5),
                new Train(6),
                new Train(7)
        };

        System.out.format("Result of the descending comparison two non-primitive type arrays by id when first" +
                        " one is lexicographically grater: %d\n",
                Arrays.compare(firstObj, secondObj, new TrainById()));
    }

    /**
     * Comparing first with second non-primitive type arrays lexicographically over the specified ranges.
     * <p>@implNote  Comparing subsequent indexes using Arrays.mismatch().</p>
     * <p>Return:<br>
     * - less than 0 when first found element is smaller;<br>
     * - greater than 0 when first found element is grater;<br>
     * - 0 when there is no mismatch.</p>
     * <p>Throws:<br>
     * - <b>IllegalArgumentException</b> - if aFromIndex > aToIndex or if bFromIndex > bToIndex;<br>
     * - <b>ArrayIndexOutOfBoundsException</b> - if aFromIndex < 0 or aToIndex > a.length or if bFromIndex < 0 or bToIndex > b.length;<br>
     * - <b>NullPointerException</b> - if either array is null.</p>
     *
     * @see Arrays#compare(int[], int[])
     */
    private static void comparingNonPrimitiveArraysOverSpecifiedRanges() {
        Train[] firstObj = new Train[]{new Train(0), new Train(2), new Train(4),
                new Train(6), new Train(8), new Train(10),
                new Train(12), new Train(14), new Train(16)
        };
        Train[] secondObj = new Train[]{new Train(1), new Train(3), new Train(5),
                new Train(7), new Train(9), new Train(11),
                new Train(13), new Train(15), new Train(17)
        };

        System.out.format("Result of the comparison two non-primitive type arrays by id when first" +
                        " one (between given indexes) is lexicographically less: %d\n",
                Arrays.compare(firstObj, 2, 5, secondObj, 2, 5));
    }

}

/**
 * Train class with id.
 *
 * @implNote Implements {@link Comparable} for comparing by id.
 */
final class Train implements Comparable<Train> {
    private final int id;

    public Train(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Train o) {
        return Integer.compare(this.getId(), o.getId());
    }
}

/**
 * Custom comparator, comparing Trains by id.
 *
 * @see Train
 */
final class TrainById implements Comparator<Train> {
    @Override
    public int compare(Train o1, Train o2) {
        return o2.compareTo(o1);
    }
}
