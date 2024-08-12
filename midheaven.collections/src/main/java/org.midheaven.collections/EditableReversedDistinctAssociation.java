package org.midheaven.collections;

import java.util.function.BiFunction;

class EditableReversedDistinctAssociation<K,V> extends ReversedDistinctAssociation<K, V> implements EditableDistinctAssociation<K,V> {

    EditableReversedDistinctAssociation(EditableDistinctAssociation<V,K> original){
        super(original);
    }

    protected EditableDistinctAssociation<V,K> original(){
        return (EditableDistinctAssociation<V,K>)super.original();
    }

    public EditableDistinctAssociation<V, K> reverse(){
        return original();
    }

    @Override
    public boolean setValue(K key, V value) {
        return this.original().setKey(key, value);
    }

    @Override
    public void computeValue(K key, BiFunction<K, V, V> computation) {
        this.original().computeKey(key, computation);
    }

    @Override
    public void computeKey(V value, BiFunction<V, K, K> computation) {
        this.original().computeValue(value, computation);
    }

    @Override
    public boolean setKey(V value, K key) {
        return this.original().setValue(value, key);
    }



}
