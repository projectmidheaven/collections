package org.midheaven.collections;

import org.midheaven.lang.Maybe;

/**
 * An {@link ResizableAssociation} where the keys and the values are unique.
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public interface ResizableDistinctAssociation<K,V> extends ResizableAssociation<K,V>, EditableDistinctAssociation<K,V> {

    void putKey(V value, K key);

    Maybe<K> removeValue(V value);

    ResizableDistinctAssociation<V, K> reverse();
}
