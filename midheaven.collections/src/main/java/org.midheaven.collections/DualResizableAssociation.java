package org.midheaven.collections;

public class DualResizableAssociation<K,V> extends DualEditableAssociation<K,V> implements ResizableDistinctAssociation<K,V> {

    public ResizableDistinctAssociation<V, K> reverse(){
        return new ResizableReversedDistinctAssociation<>(this);
    }

}
