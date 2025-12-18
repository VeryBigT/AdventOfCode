package util.collection;

import java.util.*;

public abstract class Collections {
    public static <E> Set<E> setOf(E... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    public static <E> List<E> listOf(E... elements) {
        return Arrays.asList(elements);
    }

    public static <E> boolean containsAny(Collection<E> col1, Collection<E> col2) {
        return col1.stream().anyMatch(col2::contains);
    }

    public static <E> void reduce(Collection<Set<E>> col) {
        for (Set<E> col1: col) {
            for (Set<E> col2: col) {
                if (col1.equals(col2))
                    continue;
                if (containsAny(col1, col2)) {
                    col1.addAll(col2);
                    col.remove(col2);
                    reduce(col);
                    return;
                }
            }
        }
    }

    public static <E> void reduce(Set<E>... cols) {
        reduce(listOf(cols));
    }
}
