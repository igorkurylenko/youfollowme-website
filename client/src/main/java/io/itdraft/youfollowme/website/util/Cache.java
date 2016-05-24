package io.itdraft.youfollowme.website.util;

import com.google.gwt.core.client.Duration;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, V> {

    private static final int DEFAULT_LIFETIME_MILLIS = 3 * 1000 * 60; // 3 min

    private Map<K, CacheItem<V>> map = new HashMap<>();

    private Cache() {
    }

    public void put(K key, V value) {
        put(key, value, DEFAULT_LIFETIME_MILLIS);
    }

    public void put(K key, V value, int lifetimeMillis) {
        map.put(key, new CacheItem<V>(value, lifetimeMillis));
    }

    public V get(K key) {
        CacheItem<V> cacheItem = map.get(key);

        return cacheItem == null ? null :
                cacheItem.isExpired() ? null : cacheItem.value;
    }

    public static <K, V> Cache<K, V> create() {
        return new Cache<>();
    }

    private class CacheItem<V> {
        final Duration duration;
        final V value;
        private final int lifetimeMillis;

        public CacheItem(V value, int lifetimeMillis) {
            this.duration = new Duration();
            this.lifetimeMillis = lifetimeMillis;
            this.value = value;
        }

        boolean isExpired() {
            return duration.elapsedMillis() > lifetimeMillis;
        }
    }
}
