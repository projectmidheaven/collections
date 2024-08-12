package org.midheaven.collections;

import java.util.Map;

public class EditableDistinctAssociationBuilder {

    @SafeVarargs
    public final <K,V> EditableDistinctAssociation<K,V> ofEntries(Association.Entry<K, V>... entries){
        var map = new DualEditableAssociation<K,V>();
        for (var entry : entries){
            map.putValue(entry.key(), entry.value());
        }
        return map;
    }

    public <K, V> EditableDistinctAssociation<K,V> from(Map<K,V> map){
        var dual = new DualEditableAssociation<K,V>();
        map.forEach(dual::putValue);
        return dual;
    }
}
