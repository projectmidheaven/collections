package org.midheaven.collections;

import org.midheaven.lang.Maybe;
import org.midheaven.math.Int;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

class ReversedDistinctAssociation<K,V> implements DistinctAssociation<K,V> {

    private final DistinctAssociation<V,K> original;

    ReversedDistinctAssociation(DistinctAssociation<V,K> original){
        this.original = original;
    }

    protected DistinctAssociation<V,K> original(){
        return original;
    }

    @Override
    public Maybe<K> getKey(V value) {
        return original.getValue(value);
    }

    @Override
    public boolean containsKey(K key) {
        return original.containsValue(key);
    }

    @Override
    public boolean containsValue(V value) {
        return original.containsKey(value);
    }

    @Override
    public Association<K, V> union(Association<K, V> other, BiFunction<V, V, V> valueSelector) {
        Map<K,V> union = new HashMap<>();

        for (var entry : this){
            union.put(entry.key(), entry.value());
        }
        for (var entry : other){
            union.put(entry.key(), valueSelector.apply(union.get(entry.key()), entry.value()));
        }

        return new ImmutableAssociationMapWrapper<>(union);
    }

    @Override
    public Association<K, V> intersection(Association<K, V> other, BiFunction<V, V, V> valueSelector) {
        Map<K,V> union = new HashMap<>();

        for (var entry : other){
            if (this.containsKey(entry.key())){
                union.put(entry.key(), this.getValue(entry.key()).map(o -> valueSelector.apply(o, entry.value()) ).orElse(entry.value()));
            }
        }

        return new ImmutableAssociationMapWrapper<>(union);
    }

    @Override
    public DistinctAssortment<K> keys() {
        return original.values();
    }

    @Override
    public DistinctAssortment<V> values() {
        return original.keys();
    }

    @Override
    public K computeKeyIfAbsent(V value, Function<V, K> computation) {
        return original.computeValueIfAbsent(value, computation);
    }

    @Override
    public V computeValueIfAbsent(K key, Function<K, V> computation) {
        return original.computeKeyIfAbsent(key, computation);
    }

    @Override
    public Maybe<V> getValue(K key) {
        return original.getKey(key);
    }

    @Override
    public DistinctAssociation<V, K> reverse() {
        return original;
    }

    @Override
    public boolean contains(Entry<K, V> entry) {
        return original.containsValue(entry.key()) && original.containsKey(entry.value());
    }

    @Override
    public Collection<Entry<K, V>> asCollection() {
        // TODO create view
        return original.asCollection().stream().map(entry -> Entry.entry(entry.value(), entry.key())).toList();
    }

    @Override
    public Enumerator<Entry<K, V>> enumerator() {
        return original.map(entry -> Entry.entry(entry.value(), entry.key())).enumerator();
    }

    @Override
    public Int count() {
        return original.count();
    }

    @Override
    public boolean isEmpty() {
        return original.isEmpty();
    }
}
