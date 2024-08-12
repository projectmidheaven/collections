package org.midheaven.collections;

import org.midheaven.lang.Maybe;

class ResizableReversedDistinctAssociation<K,V> extends EditableReversedDistinctAssociation<K, V> implements ResizableDistinctAssociation<K,V> {

    ResizableReversedDistinctAssociation(ResizableDistinctAssociation<V,K> original){
        super(original);
    }

    protected ResizableDistinctAssociation<V,K> original(){
        return (ResizableDistinctAssociation<V,K>)super.original();
    }

    public ResizableDistinctAssociation<V, K> reverse(){
        return original();
    }

    @Override
    public Maybe<K> removeValue(V value) {
        return original().removeKey(value);
    }

    @Override
    public void putValue(K key, V value) {
        original().putKey(key, value);
    }

    @Override
    public void putKey(V value, K key) {
        original().putValue(value, key);
    }

    @Override
    public void clear() {
        original().clear();
    }

    @Override
    public Maybe<V> removeKey(K key) {
        return original().removeValue(key);
    }
}
