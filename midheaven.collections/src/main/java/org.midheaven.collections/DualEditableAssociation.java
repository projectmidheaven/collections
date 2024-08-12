package org.midheaven.collections;

public class DualEditableAssociation<K,V> extends DualImmutableAssociation<K,V> implements EditableDistinctAssociation<K,V> {

    @Override
    public EditableDistinctAssociation<V, K> reverse() {
        return new EditableReversedDistinctAssociation<>(this);
    }


}
