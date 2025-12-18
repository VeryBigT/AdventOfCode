package util.collection;

import java.util.HashMap;

public class BigCountMap<K> extends HashMap<K, Long> {
    public long count(K key) {
        Long value = get(key);
        return value == null ? 0L : value;
    }

    public void add(K key, long n) {
        Long value = get(key);
        if(value != null)
            n += value;
        put(key, n);
    }

    public void add(K key) {
        add(key, 1L);
    }

    public static void main(String[] args) {
        BigCountMap<Character> map = new BigCountMap<>();
        for(char c : "Mississippi".toCharArray()) {
            map.add(c);
        }
        System.out.println("M: " + map.count('M'));
        System.out.println("i: " + map.count('i'));
        System.out.println("s: " + map.count('s'));
        System.out.println("p: " + map.count('p'));
        System.out.println(map);
        for(char c : map.keySet())
            System.out.print(c);
    }
}
