package com.epam.prejap.ess.helpers.createmap;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static com.epam.prejap.ess.helpers.createmap.CreateMap.*;
import static org.assertj.core.api.Assertions.*;

public class CreateMapTest {

    private static final Class<UnsupportedOperationException> UOE = UnsupportedOperationException.class;

    private final BiFunction<String, String, String> mergingFunction =
            (value1, value2) -> value1.equals(value2)
                    ? value1
                    : value1 + ", " + value2;

    private SoftAssertions softly;

    @BeforeMethod
    void setUp() {
        softly = new SoftAssertions();
    }

    @Test(dataProvider = "unmodifiableMapDoesNotAllowModification")
    void givenAnUnmodifiableMap_whenModify_thenThrow(Map<String, String> map) {
        softly.assertThatThrownBy(() -> map.put("new key", "value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.remove("key")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.putAll(map)).isInstanceOf(UOE);
        softly.assertThatThrownBy(map::clear).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.replaceAll((key, value) -> value.toUpperCase())).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.putIfAbsent("key", "value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.remove("key", "value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.replace("key", "value", "new value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.replace("key", "value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.computeIfAbsent("new key", String::toUpperCase)).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.computeIfPresent("key", (key, value) -> value.toUpperCase())).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.compute("key", (key, value) -> value.concat("!!!"))).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.merge("key", "value", mergingFunction)).isInstanceOf(UOE);
    }

    @Test(dataProvider = "immutableMapDoesNotAllowModification")
    void givenAnImmutableMap_whenModify_thenThrow(Map<String, String> map) {
        softly.assertThatThrownBy(() -> map.put("new key", "value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.remove("key")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.putAll(map)).isInstanceOf(UOE);
        softly.assertThatThrownBy(map::clear).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.replaceAll((key, value) -> value.toUpperCase())).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.putIfAbsent("key", "value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.remove("key", "value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.replace("key", "value", "new value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.replace("key", "value")).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.computeIfAbsent("new key", String::toUpperCase)).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.computeIfPresent("key", (key, value) -> value.toUpperCase())).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.compute("key", (key, value) -> value.concat("!!!"))).isInstanceOf(UOE);
        softly.assertThatThrownBy(() -> map.merge("key", "value", mergingFunction)).isInstanceOf(UOE);
    }

    @Test(dataProvider = "unmodifiableMapAllowNullKey")
    void givenAnUnmodifiableMapWithNullKey_whenGet_entryIsPresent(Map<String, String> map) {
        assertThat(map).containsEntry(null, "valueForNullKey");
    }

    @Test(expectedExceptions = NullPointerException.class)
    void givenAnImmutableMapWithNullKey_whenGet_thenThrows() {
        Map.of(null, "valueForNullKey");
    }

    @Test(dataProvider = "unmodifiableMapAllowNullValue")
    void givenAnUnmodifiableMapWithNullValue_whenGet_entryIsPresent(Map<String, String> map) {
        assertThat(map).containsEntry("keyForNullValue", null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    void givenAnImmutableMapWithNullValue_whenGet_thenThrows() {
        Map.of("keyForNullValue", null);
    }

    @Test
    void givenAnUnmodifiableMap_whenModifyingUnderlyingMap_thenUnmodifiableMapReflectChanges() {
        Map<String, String> mutableMap = new HashMap<>();
        mutableMap.put("key", "value");
        mutableMap.put("key2", "value2");

        Map<String, String> unmodifiableMap = getUnmodifiableMapFrom(mutableMap);
        mutableMap.remove("key2");
        assertThat(unmodifiableMap).doesNotContainKey("key2");

        mutableMap.put("new key", "new value");
        assertThat(unmodifiableMap).containsEntry("new key", "new value");
    }

    @DataProvider(name = "unmodifiableMapDoesNotAllowModification")
    Object[][] getUnmodifiableMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("a", "b");
        map.put("aa", "gg");
        map.put("asdf", "gegbert");
        return new Object[][]{{
                getUnmodifiableMapFrom(map)
        }};
    }

    @DataProvider(name = "immutableMapDoesNotAllowModification")
    Object[][] getImmutableCollection() {
        return new Object[][]{{
                getImmutableCollectionFrom("key", "value")
        }};
    }

    @DataProvider(name = "unmodifiableMapAllowNullKey")
    Object[][] getUnmodifiableMapWithNullKey() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "valueForNullKey");
        return new Object[][]{{
                getUnmodifiableMapFrom(map)
        }};
    }

    @DataProvider(name = "unmodifiableMapAllowNullValue")
    Object[][] getUnmodifiableMapWithNullValue() {
        Map<String, String> map = new HashMap<>();
        map.put("keyForNullValue", null);
        return new Object[][]{{
                getUnmodifiableMapFrom(map)
        }};
    }
}
