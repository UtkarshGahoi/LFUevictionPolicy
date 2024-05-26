package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {
    private int capacity;
    private Map<Integer, CacheNode> cacheMap;
    private PriorityQueue<CacheNode> minHeap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.minHeap = new PriorityQueue<>((a, b) -> {
            if (a.frequency != b.frequency) {
                return a.frequency - b.frequency;
            } else {
                return a.key - b.key;
            }
        });

    }

    // Get value corresponding to key from the cache
    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        CacheNode node = cacheMap.get(key);
        // Update frequency and reorder min heap
        node.frequency++;
        minHeap.remove(node);
        minHeap.offer(node);
        return node.value;
    }

    // Put key-value pair into the cache
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (cacheMap.containsKey(key)) {
            CacheNode node = cacheMap.get(key);
            node.value = value;
            node.frequency++;
            minHeap.remove(node);
            minHeap.offer(node);
        } else {
            if (cacheMap.size() >= capacity) {
                evict(); // Evict least frequently used item
            }
            CacheNode newNode = new CacheNode(key, value, 1);
            cacheMap.put(key, newNode);
            minHeap.offer(newNode);
        }
    }

    // Evict least frequently used item from the cache
    private void evict() {
        CacheNode node = minHeap.poll();
        cacheMap.remove(node.key);
    }

}
