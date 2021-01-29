package com.epam.prejap.ess.helpers.list.unmodifiablevsof;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test(groups = "UnmodifiableListVsListOf")
public class UnmodifiableListVsListOfTest {

    /**
     * Proves that {@link Collections#unmodifiableList(List)} creates list with same elements as List given as argument.
     * @param originalList List of objects that unmodifiableList wraps.
     */
    @Test(dataProvider = "listWithObjects") // given
    public void shouldCreateUnmodifiableListWithOriginalElements(List<Object> originalList) {
        // when
        List<Object> unmodifiableList = Collections.unmodifiableList(originalList);

        // then
        assertEquals(unmodifiableList, originalList, "Lists should have the same elements but do not");
    }

    /**
     * Proves that {@link Collections#unmodifiableList(List)} wraps List from argument by showing that
     * changes on original list are reflected on {@code unmodifiableList}.
     * @param originalList List of objects that unmodifiableList wraps.
     */
    @Test(dataProvider = "listWithObjects") // given
    public void modifyingOriginalListShouldBeReflectedOnItsUnmodifiableList(List<Object> originalList) {
        // given
        List<Object> unmodifiableList = Collections.unmodifiableList(originalList);

        // when
        originalList.add(new Object());
        originalList.set(0, new Object());
        originalList.remove(1);

        // then
        assertEquals(unmodifiableList, originalList, "Lists should have the same elements but do not");
    }

    /**
     * Proves that {@link Collections#unmodifiableList(List)} wraps List from argument by showing that
     * it delegates its allowed functionality to original list.
     */
    @Test
    public void getOnUnmodifiableListShouldInvokeGetOnOriginalList() {
        // given
        List<Object> mockOriginalList = mock(List.class);
        List<Object> unmodifiableList = Collections.unmodifiableList(mockOriginalList);

        // when
        unmodifiableList.get(0);

        // then
        verify(mockOriginalList).get(0);
    }

    /**
     * Proves that {@link Collections#unmodifiableList(List)} wraps List from argument by showing that
     * it delegates its allowed functionality to original list.
     */
    @Test
    public void SizeOnUnmodifiableListShouldInvokeSizeOnOriginalList() {
        // given
        List<Object> mockOriginalList = mock(List.class);
        List<Object> unmodifiableList = Collections.unmodifiableList(mockOriginalList);

        // when
        unmodifiableList.size();

        // then
        verify(mockOriginalList).size();
    }

    /**
     * Proves that {@link Collections#unmodifiableList(List)} wraps List from argument by showing that
     * it delegates its allowed functionality to original list.
     */
    @Test
    public void hashCodeOnUnmodifiableListShouldInvokeHashCodeOnOriginalList() {
        // given
        List<Object> mockOriginalList = mock(List.class);
        List<Object> unmodifiableList = Collections.unmodifiableList(mockOriginalList);

        // when
        unmodifiableList.hashCode();

        // then
        verify(mockOriginalList).hashCode();
    }

    /**
     * Proves that {@link Collections#unmodifiableList(List)} returns a List with disabled modification functionality.
     */
    @Test(expectedExceptions = {UnsupportedOperationException.class}) // then
    public void setOnUnmodifiableListShouldNotBeAllowed() {
        // given
        List<Object> unmodifiableList = Collections.unmodifiableList(Collections.emptyList());

        // when
        unmodifiableList.set(0, new Object());
    }

    /**
     * Proves that {@link Collections#unmodifiableList(List)} returns a List with disabled modification functionality.
     */
    @Test(expectedExceptions = {UnsupportedOperationException.class}) // then
    public void clearOnUnmodifiableListShouldNotBeAllowed() {
        // given
        List<Object> unmodifiableList = Collections.unmodifiableList(Collections.emptyList());

        // when
        unmodifiableList.clear();
    }

    /**
     * Proves that {@link List#of(Object[])} creates a {@link List} containing elements from passed arguments.
     */
    @Test
    public void shouldCreateListOfObjects() {
        // given
        Object[] objects = Stream.generate(Object::new).limit(3).toArray();

        // when
        List<Object> listOf = List.of(objects);

        // then
        for (int i = 0; i < listOf.size(); i++) {
            assertEquals(listOf.get(i), objects[i], String.format(
                    "Object on %d index should be the same as argument number %d in List.of()", i, i));
        }
    }

    /**
     * Proves that {@link List#of(Object[])} holds elements on its own and doesn't use Object array passed as argument.
     */
    @Test
    public void modifyingOriginalArrayShouldNotModifyCreatedListOf() {
        final int modifiedIndex = 0;
        // given
        Object[] objects = Stream.generate(Object::new).limit(3).toArray();
        List<Object> listOf = List.of(objects);

        // when
        Object originalObject = objects[modifiedIndex];
        objects[modifiedIndex] = new Object();

        // then
        assertEquals(listOf.get(modifiedIndex), originalObject);
        assertNotEquals(listOf.get(modifiedIndex), objects[modifiedIndex]);
    }

    /**
     * Proves that {@link List#of()} returns a List with disabled modification functionality.
     */
    @Test(expectedExceptions = {UnsupportedOperationException.class}) // then
    public void setOnListOfShouldNotBeAllowed() {
        // given
        List<Object> listOf = List.of();

        // when
        listOf.set(0, new Object());
    }

    /**
     * Proves that {@link List#of()} returns a List with disabled modification functionality.
     */
    @Test(expectedExceptions = {UnsupportedOperationException.class}) // then
    public void clearOnListOfShouldNotBeAllowed() {
        // given
        List<Object> listOf = List.of();

        // when
        listOf.clear();
    }

    @DataProvider
    public Object[][] listWithObjects() {
        return new Object[][]{
                {Stream.generate(Object::new).limit(3).collect(Collectors.toCollection(ArrayList::new))}
        };
    }
}
