package org.midheaven.collections;

import org.midheaven.lang.Maybe;
import org.midheaven.math.Int;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;

public class DualAssociationBase<K, V> {

    private final ResizableAssociation<K,V> direct;
    private final ResizableAssociation<V,K> reverse;

    DualAssociationBase(){
        this(
            Association.builder().resizable().empty(),
            Association.builder().resizable().empty()
        );
    }

    DualAssociationBase(
           ResizableAssociation<K,V> direct,
           ResizableAssociation<V,K> reverse
    ){
        this.direct = direct;
        this.reverse = reverse;
    }

    public DistinctAssociation<K, V> union(Association<K, V> association, BiFunction<V, V, V> valueSelector) {
        var unionDirect = Association.builder().resizable().from(direct);
        var unionReversed = Association.builder().resizable().from(reverse);

        for (var entry : association){
            var newValue = unionDirect.getValue(entry.key()).map( value -> valueSelector.apply(value,entry.value())).orElse(entry.value());
            unionDirect.putValue(entry.key(),newValue);
            unionReversed.putValue(newValue, entry.key());
        }

        return new DualImmutableAssociation<>(unionDirect, unionReversed);
    }

    public DistinctAssociation<K, V> intersection(Association<K, V> association, BiFunction<V, V, V> valueSelector) {
        var intersectionDirect = Association.builder().resizable().from(direct);
        var intersectionReversed = Association.builder().resizable().from(reverse);

        for (var entry : association){
            if (direct.containsKey(entry.key())){
                var newValue = valueSelector.apply(direct.getValue(entry.key()).orNull(), entry.value());
                intersectionDirect.putValue(entry.key(), newValue);
                intersectionReversed.putValue(newValue, entry.key());
            }
        }

        return new DualImmutableAssociation<>(intersectionDirect, intersectionReversed);
    }

    public boolean setValue(K key, V value) {
        if (containsKey(key)){
            putValue(key, value);
            return true;
        }
        return false;
    }

    public boolean setKey(V value, K key) {
        return setValue(key, value);
    }

    public Maybe<K> getKey(V value) {
        return reverse.getValue(value);
    }

    public boolean containsKey(K key) {
        return direct.containsKey(key);
    }

    public boolean containsValue(V value) {
        return reverse.containsKey(value);
    }

    public DistinctAssortment<K> keys() {
        return direct.keys();
    }

    public DistinctAssortment<V> values() {
        return reverse.keys();
    }

    public Maybe<K> removeValue(V value) {
        var possibleKey = reverse.removeKey(value);
        possibleKey.ifPresent(direct::removeKey);
        return possibleKey;
    }

    public Maybe<V> removeKey(K key) {
        var possibleValue = direct.removeKey(key);
        possibleValue.ifPresent(reverse::removeKey);
        return possibleValue;
    }

    public void putValue(K key, V value) {
        removeKey(key);
        if (reverse.containsKey(value)){
            throw new IllegalArgumentException(value + " is already present in association");
        }
        direct.putValue(key, value);
        reverse.putValue(value, key);
    }

    public void putKey(V value, K key) {
        putValue(key, value);
    }

    public void clear() {
        direct.clear();
        reverse.clear();
    }

    public boolean contains(Association.Entry<K, V> entry) {
        return direct.containsKey(entry.key()) && reverse.containsKey(entry.value());
    }

    public Collection<Association.Entry<K, V>> asCollection() {
        // TODO make a collection view of both associations
        return Collections.unmodifiableCollection(direct.asCollection());
    }

    public Enumerator<Association.Entry<K, V>> enumerator() {
        return direct.enumerator();
    }

    public Int count() {
        return direct.count();
    }

    public boolean isEmpty() {
        return direct.isEmpty();
    }

    public Maybe<V> getValue(K key) {
        return direct.getValue(key);
    }

}
