package io.asfjava.ui.core;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public abstract class SimpleFactory<K, V> {

    private final Map<K, V> map = new ConcurrentHashMap<>();

    public Optional<V> get(K key) {
        return Optional.ofNullable(map.get(key));
    }

    void register(Supplier<K> key, V value) {
        map.put(key.get(), value);
    }
}
