package org.example;

import java.util.HashMap;
import java.util.Map;

public class LFUCacheManager {
    private Map<String, LFUCache> caches;

    public LFUCacheManager() {
        this.caches = new HashMap<>();
    }

    // Create a new LFUCache with given capacity and add it to the manager
    public void createCache(String cacheName, int capacity) {
        if (!caches.containsKey(cacheName)) {
            caches.put(cacheName, new LFUCache(capacity));
        }
    }

    // Get LFUCache instance by name
    public LFUCache getCache(String cacheName) {
        return caches.get(cacheName);
    }
}
