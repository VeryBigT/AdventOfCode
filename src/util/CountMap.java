package util;

import java.util.HashMap;

public class CountMap<K> extends HashMap<K, Integer> {
    public int count(K key) {
        Integer value = get(key);
        return value == null ? 0 : value;
    }

    public void add(K key, int n) {
        Integer value = get(key);
        if(value != null)
            n += value;
        put(key, n);
    }

    public void add(K key) {
        add(key, 1);
    }

    public static void main(String[] args) {
        CountMap<Character> map = new CountMap<>();
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
