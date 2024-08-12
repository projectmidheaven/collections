package org.midheaven.collections;

import java.util.Map;

public class ResizableDistinctAssociationBuilder {


    public <K,V> ResizableDistinctAssociation<K,V> empty() {
        return new DualResizableAssociation<>();
    }

    @SafeVarargs
    public final <K,V> ResizableDistinctAssociation<K,V> ofEntries(Association.Entry<K, V>... entries){
        if (entries == null || entries.length == 0){
            return empty();
        }
        var map = new DualResizableAssociation<K,V>();
        for (var entry : entries){
            map.putValue(entry.key(), entry.value());
        }
        return map;
    }

    public <K, V> ResizableDistinctAssociation<K,V> from(Map<K,V> map){
        var dual = new DualResizableAssociation<K,V>();
        map.forEach(dual::putValue);
        return dual;
    }


}
