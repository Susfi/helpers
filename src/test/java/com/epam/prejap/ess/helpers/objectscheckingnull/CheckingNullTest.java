package com.epam.prejap.ess.helpers.objectscheckingnull;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckingNullTest {

    private final static String NULL_FRUIT_MESSAGE = "Fruit cannot be null!";
    private final static String DEFAULT_FRUIT_NAME = "banana";

    @Test
    public void whenAddFruitAsNullShouldThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CheckingNull.addFruit(null));
    }

    @Test(dependsOnMethods = "whenAddFruitAsNullShouldThrowsNullPointerException")
    public void whenAddFruitAsNullShouldDisplayMessage() {
        //when
        String actualMessage = "";
        try {
            CheckingNull.addFruit(null);
        } catch (NullPointerException e) {
            actualMessage = e.getMessage();
        }

        assertEquals(actualMessage, NULL_FRUIT_MESSAGE);
    }

    @Test
    public void toStringShouldReturnDefaultNameIfOriginalIsNull() {
        //given
        CheckingNull.Fruit fruit = new CheckingNull.Fruit(null);

        //when
        String actualName = fruit.toString();

        //then
        assertEquals(actualName, DEFAULT_FRUIT_NAME);
    }

}
