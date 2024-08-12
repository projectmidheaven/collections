package org.midheaven.collections;

import org.midheaven.lang.Maybe;
import org.midheaven.math.Int;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class EmptyDistinctAssociation<K,V> implements DistinctAssociation<K,V> {

    private static final DistinctAssociation ME = new EmptyDistinctAssociation();

    public static <Q,W> DistinctAssociation instance(){
        return ME;
    }

    @Override
    public Maybe<K> getKey(V value) {
        return Maybe.none();
    }

    @Override
    public boolean containsKey(K k) {
        return false;
    }

    @Override
    public boolean containsValue(V v) {
        return false;
    }

    @Override
    public Association<K, V> union(Association<K, V> association, BiFunction<V, V, V> biFunction) {
        return association;
    }

    @Override
    public Association<K, V> intersection(Association<K, V> association, BiFunction<V, V, V> biFunction) {
        return this;
    }

    @Override
    public V computeValueIfAbsent(K key, Function<K, V> computation) {
        return computation.apply(key);
    }

    @Override
    public DistinctAssortment<K> keys() {
        return DistinctAssortment.builder().empty();
    }

    @Override
    public DistinctAssortment<V> values() {
        return DistinctAssortment.builder().empty();
    }

    @Override
    public Maybe<V> getValue(K k) {
        return Maybe.none();
    }

    @Override
    public K computeKeyIfAbsent(V value, Function<V, K> computation) {
        return computation.apply(value);
    }

    @Override
    public DistinctAssociation<V, K> reverse() {
        return ME;
    }

    @Override
    public boolean contains(Entry<K, V> kvEntry) {
        return false;
    }

    @Override
    public Collection<Entry<K, V>> asCollection() {
        return Collections.emptySet();
    }

    @Override
    public Enumerator<Entry<K, V>> enumerator() {
        return Enumerator.empty();
    }

    @Override
    public Int count() {
        return Int.ZERO;
    }


    @Override
    public boolean isEmpty() {
        return true;
    }
}
