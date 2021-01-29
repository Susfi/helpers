package com.epam.prejap.ess.helpers.objectscheckingnull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * My task was to compare the methods from the Objects class that check whether the object is null.
 *
 * @author Paulina Ksienżyk
 */
class CheckingNull {

    private final static String NULL_FRUIT_MESSAGE = "Fruit cannot be null!";
    final static List<Fruit> fruitBasket = List.of(new Fruit("melon"),
            new Fruit("apple"), new Fruit("pear"));

    /**
     * Objects.isNull() returns true if the provided reference is null.
     * The opposite method is Objects.nonNull() - returns true if the provided reference in non-null.
     * <p>
     * Both methods exists to be used as Predicate (Objects::isNull)
     * and can be used to filter in objects able to store nulls.
     */
    public static void main(String[] args) {

        Fruit nullFruit = null;

        System.out.println("Is nullFruit null? : " + Objects.isNull(nullFruit));

        System.out.println("\nNon null objects in fruit basket: ");
        Stream.of(fruitBasket)
                .filter(Objects::nonNull)
                .forEach(System.out::println);

        Fruit fruitWithNullName = new Fruit(null);
        System.out.println("\nFruit with null name is: " + fruitWithNullName.toString());

        System.out.println();

        differences().forEach(System.out::println);
    }

    /**
     * Objects.RequireNonNull() allow us to validate the parameter passed by the method or constructor.
     * If the passed parameter is null, a NullPointerException is thrown.
     * If we want to customize our NullPointerException message we can use overloaded method which takes String
     * or Supplier<String>.
     *
     * @param fruit
     * @throws NullPointerException if fruit is null.
     * @see Fruit
     */
    static void addFruit(Fruit fruit) {
        Objects.requireNonNull(fruit, NULL_FRUIT_MESSAGE);
        fruitBasket.add(fruit);
    }

    /**
     * Objects.requireNonNullElse() returns the first argument if is not null, or the second (default) argument.
     * Objects.requireNonNullElseGet() takes Supplier as the second argument.
     * Method {@link #toString()} returns name of fruit if is not null, otherwise it returns default name - "banana".
     *
     * @throws NullPointerException if both arguments are nulls.
     */
    static class Fruit {
        private final String name;

        public Fruit(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return Objects.requireNonNullElse(name, "banana");
        }
    }

    private static List<String> differences() {
        return List.of("isNull() and nonNull() are non-generic",
                "isNull() and nonNull() can be used as Predicate to filter",
                "requireNonNull()...Else()...ElseGet() throw NullPointerException",
                "requireNonNull() exists to validate parameters",
                "requireNonNullElse()...ElseGet() allow us to obtain the default argument if the first is null");
    }
}
