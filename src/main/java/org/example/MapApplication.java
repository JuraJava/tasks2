package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapApplication {
    public static void main(String[] args) {
        Map<List<String>, String> map = new HashMap<>();
        final List<String> key = new ArrayList<>();
        map.put(key, "Hello");
        key.add("World");
        key.clear();
        System.out.println(map.remove(key));
    }
}
