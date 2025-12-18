package util.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListMap<K, V> extends HashMap<K, List<V>> {
    public void add(K key, V value) {
        List<V> values = get(key);
        if(values == null) {
            values = new ArrayList<>();
            put(key, values);
        }
        values.add(value);
    }
}
