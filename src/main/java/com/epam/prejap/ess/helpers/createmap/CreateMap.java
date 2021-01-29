package com.epam.prejap.ess.helpers.createmap;

import java.util.*;

/**
 * <h2>Task: unmodifiableMap vs Map.of</h2>
 * <p>
 * Both methods can be used to obtain a map, but there are important differences
 * between them:
 *
 * <br><ol><li> Both methods come from different places:<ul><li>
 * {@link Collections#unmodifiableMap(Map)} is part of the {@link Collections}
 * utility class since Java 1.2.</li>
 * <li>{@link Map#of(Object, Object)} is part of the {@link Map} interface since
 * Java 9.</li></ul></li>
 *
 * <br><li>We need to pass different parameters to both methods:<ul><li>
 * {@link Collections#unmodifiableMap(Map)} takes a map as a parameter.</li>
 * <li>{@link Map#of(Object, Object)} takes key-value pairs as a parameter.
 * We can define one such pair and get a map with a single mapping.
 * Up to 10 such mappings can be specified. If we want more, we should use
 * {@link Map#ofEntries(Map.Entry[])} method according to JEP-269.</li></ul></li>
 *
 * <br><li>Both maps do not allow any modifications to the returned map. Each of
 * the modifier methods throws {@code UnsupportedOperationException}.</li>
 *
 * <br><li> {@link Collections#unmodifiableMap(Map)} allows null key,
 * but {@link Map#of(Object, Object)} throws {@code NullPointerException} when you
 * try to add one.</li>
 *
 * <br><li> {@link Collections#unmodifiableMap(Map)} allows null values,
 * but {@link Map#of(Object, Object)} throws {@code NullPointerException} when you
 * try to add one.</li>
 *
 * <br><li> As stated in docs - {@link Collections#unmodifiableMap(Map)} returns
 * an unmodifiable view of the specified map. An Unmodifiable Map is just
 * a wrapper over a modifiable map and it doesn't allow modifications to it
 * directly, but the underlying mutable map can still be changed and
 * the modifications are reflected in the Unmodifiable map as well. An Immutable
 * Map, on the other hand, contains its own private data and doesn't allow
 * modifications to it. Therefore, the data cannot change in any way once
 * an instance of the Immutable Map is created.</li></li></ol>
 *
 * <p>In the tests, you can see examples of each of the above statements
 * (ctrl + shift + T on {@link CreateMap}).</p>
 *
 * @author Łukasz Bulczak
 * @see Collections
 * @see Map
 * @see <a href="http://openjdk.java.net/jeps/269">JEP 269: Convenience Factory
 * Methods for Collections</a>
 */
class CreateMap {

    /**
     * Returns an unmodifiable view of the specified map. Query operations
     * on the returned map "read through" to the specified map, and attempts
     * to modify the returned map, whether direct or via its collection views,
     * result in an {@code UnsupportedOperationException}.<p>
     * <p>
     * The returned map will be serializable if the specified map
     * is serializable.
     *
     * @param map the map for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified map.
     */
    static Map<String, String> getUnmodifiableMapFrom(Map<String, String> map) {
        return Collections.unmodifiableMap(map);
    }

    /**
     * For the purposes of the presentation, I used method that returns
     * an unmodifiable map containing a single mapping.
     *
     * @param key   the mapping's key
     * @param value the mapping's value
     * @return a {@code Map} containing the specified mapping
     * @throws NullPointerException if the key or the value is {@code null}
     * @since Java 9
     */
    static Map<String, String> getImmutableCollectionFrom(String key, String value) {
        return Map.of(key, value);
    }
}
