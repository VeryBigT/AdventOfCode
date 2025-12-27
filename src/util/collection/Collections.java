package util.collection;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Collections {
    @SafeVarargs
    public static <E> Set<E> setOf(E... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    @SafeVarargs
    public static <E> List<E> listOf(E... elements) {
        return Arrays.asList(elements);
    }

    /**
     * Checks whether the both collections have an intersection.
     * @param col1 the first collection
     * @param col2 the second collection
     * @return {@code true} if the two collections have matching elements, otherwise {@code false}
     * @param <E> the type of the elements
     */
    public static <E> boolean hasIntersection(Collection<E> col1, Collection<E> col2) {
        return col1.stream().anyMatch(col2::contains);
    }

    public static <E> boolean hasMatchingElements(Collection<E> col1, Collection<E> col2) {
        return hasIntersection(col1, col2);
    }

    /**
     * Creates the union of two collections.
     * @param col1 the first collection
     * @param col2 the second collection
     * @return a set of all elements that are in col1 or in col2 (or both)
     * @param <E> the type of the elements
     */
    public static <E> Set<E> union(Collection<E> col1, Collection<E> col2) {
        Set<E> set = new HashSet<>(col1);
        set.addAll(col2);
        return set;
    }

    /**
     * Creates the intersection of two collections.
     * @param col1 the first collection
     * @param col2 the second collection
     * @return a set of all elements that are both in col1 und col2
     * @param <E> the type of the elements
     */
    public static <E> Set<E> intersection(Collection<E> col1, Collection<E> col2) {
        return col1.stream().filter(col2::contains).collect(Collectors.toSet());
    }

    public static <E> void reduce(Collection<Set<E>> col) {
        for (Set<E> col1: col) {
            for (Set<E> col2: col) {
                if (col1.equals(col2))
                    continue;
                if (hasIntersection(col1, col2)) {
                    col1.addAll(col2);
                    col.remove(col2);
                    reduce(col);
                    return;
                }
            }
        }
    }

    @SafeVarargs
    public static <E> void reduce(Set<E>... cols) {
        reduce(listOf(cols));
    }
}
