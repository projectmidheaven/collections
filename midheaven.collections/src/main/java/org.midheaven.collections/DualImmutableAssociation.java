package org.midheaven.collections;

import java.util.function.BiFunction;
import java.util.function.Function;

public class DualImmutableAssociation<K,V> extends DualAssociationBase<K,V> implements DistinctAssociation<K,V> {

    DualImmutableAssociation(){}

    DualImmutableAssociation(
            ResizableAssociation<K, V> direct,
            ResizableAssociation<V, K> revered
    ) {
        super(direct, revered);
    }

    @Override
    public V computeValueIfAbsent(K key, Function<K, V> computation) {
        return getValue(key).orElseGet(() -> computation.apply(key));
    }

    @Override
    public K computeKeyIfAbsent(V value, Function<V, K> computation) {
        return getKey(value).orElseGet(() -> computation.apply(value));
    }

    public void computeKey(V value, BiFunction<V, K, K> computation) {
        getKey(value).ifPresent(key -> this.setKey(value, computation.apply(value, key)));
    }

    public void computeValue(K key, BiFunction<K, V, V> computation) {
        getValue(key).ifPresent(value -> this.setValue(key, computation.apply(key, value)));
    }

    @Override
    public DistinctAssociation<V, K> reverse() {
        return new ReversedDistinctAssociation<>(this);
    }

    @Override
    public boolean containsAll(Iterable<? extends Entry<K, V>> all) {
        return DistinctAssociation.super.containsAll(all);
    }
}
