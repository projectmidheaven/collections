package org.midheaven.collections;

import org.midheaven.lang.Maybe;

import java.util.function.Function;

/**
 * An {@link Association} where the keys and the values are unique.
 * {@code DistinctAssociation} allows for retrieval of the value associated with the key, and the key associated with the value.
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public interface DistinctAssociation<K,V> extends Association<K,V> {

    static DistinctAssociationBuilder builder(){
        return new DistinctAssociationBuilder();
    }

    Maybe<K> getKey(V value);

    DistinctAssortment<V> values();

    K computeKeyIfAbsent(V value, Function<V, K> computation);

    DistinctAssociation<V,K> reverse();
}
