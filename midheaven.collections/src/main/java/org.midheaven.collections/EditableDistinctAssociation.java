package org.midheaven.collections;

import java.util.function.BiFunction;

/**
 * An {@link ResizableAssociation} where the keys and the values are unique.
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public interface EditableDistinctAssociation<K,V> extends DistinctAssociation<K,V> , EditableAssociation<K,V> {

    EditableDistinctAssociation<V, K> reverse();


    boolean setKey(V value, K key);

    void computeKey(V value, BiFunction<V, K, K> computation);
}
