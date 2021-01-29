package com.epam.prejap.ess.helpers.list.unmodifiablevsof;

import java.util.Collections;
import java.util.List;

/**
 * <p>{@link Collections#unmodifiableList(List)} vs. {@link List#of()}</p>
 *
 * <p>unmodifiableList wraps List given as an argument. Returned List has modification operations disabled
 * by throwing UnsupportedOperationException.</p>
 * <p>Under the hood this unmodifiableList uses original list and delegates allowed functionality to it,
 * therefore all modifications on original list are reflected on unmodifiableList.</p>
 * <p></p>
 * <p>Under the hood the List.of allocates new generic array or objects (optimization for 1 and 2 element lists), to hold objects
 * passed as arguments. Returned List has modification operations disabled by throwing UnsupportedOperationException.</p>
 * <p>Although an array can be passed as an argument, List.of still creates its own array to hold elements, this way
 * all original array modifications aren't reflected on the returned List.</p>
 * <p></p>
 * <p>Demonstration is in the {@code UnmodifiableVsOfTest} class.</p>
 *
 * @author Maciej Krawczyk
 */
class UnmodifiableListVsListOf {
}
