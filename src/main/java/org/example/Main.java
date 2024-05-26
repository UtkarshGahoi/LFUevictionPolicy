package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        LFUCacheManager cacheManager = new LFUCacheManager();

        // Create a new LFUCache named "cache1" with capacity 3
        cacheManager.createCache("cache1", 3);

        // Get LFUCache instance by name
        LFUCache cache1 = cacheManager.getCache("cache1");

        // Put some key-value pairs into the cache
        cache1.put(1, 10);
        cache1.put(2, 20);
        cache1.put(3, 30);

        // Get values from the cache
        System.out.println(cache1.get(1)); // Output: 10
        System.out.println(cache1.get(2)); // Output: 20
        System.out.println(cache1.get(3)); // Output: 30

        // Put another key-value pair into the cache
        cache1.put(4, 40);

        // Get value from the cache (key=1 should be evicted as it's the least frequently used)
        System.out.println(cache1.get(1));

        }
    }