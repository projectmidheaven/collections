package org.midheaven.collections;

import java.util.Map;

public class DistinctAssociationBuilder {

    @SafeVarargs
    public final <K,V> DistinctAssociation<K,V> ofEntries(Association.Entry<K, V>... entries){
        if (entries == null || entries.length == 0){
            return empty();
        }
        var map = new DualImmutableAssociation<K,V>();
        for (var entry : entries){
            map.putValue(entry.key(), entry.value());
        }
        return map;
    }

    public <K, V> DistinctAssociation<K,V> from(Map<K,V> map){
        var dual = new DualImmutableAssociation<K,V>();
        map.forEach(dual::putValue);
        return dual;
    }

    public <K, V> DistinctAssociation<K,V> empty(){
        return EmptyDistinctAssociation.instance();
    }

    public EditableDistinctAssociationBuilder editable(){
        return new EditableDistinctAssociationBuilder();
    }

    public ResizableDistinctAssociationBuilder resizable(){
        return new ResizableDistinctAssociationBuilder();
    }
}
