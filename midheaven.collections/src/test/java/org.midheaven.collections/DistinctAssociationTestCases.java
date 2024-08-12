package org.midheaven.collections;

import org.junit.jupiter.api.Test;
import org.midheaven.lang.Maybe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DistinctAssociationTestCases {

    @Test
    public void putIntoImmutable() {
        var distinct = DistinctAssociation.builder().empty();

        assertEquals(0, distinct.count().toInt());

    }

    @Test
    public void putIntoEmptyResizable() {
        var distinct = DistinctAssociation.builder().resizable().empty();

        distinct.putValue("A", 1);
        distinct.putValue("B", 2);
        distinct.reverse().putValue(3, "C");

        assertThrows(IllegalArgumentException.class, () -> distinct.putValue("D", 1), "Duplicated value was accepted");

        assertEquals(Maybe.of(1), distinct.getValue("A"));
        assertEquals(Maybe.of(2), distinct.getValue("B"));
        assertEquals(Maybe.of(3), distinct.getValue("C"));
    }

    @Test
    public void putIntoEditable() {
        var distinct = DistinctAssociation.builder().editable().ofEntries(
                Association.Entry.entry("A", 1),
                Association.Entry.entry("B", 2),
                Association.Entry.entry("C", 3)
        );

        assertEquals(Maybe.of(1), distinct.getValue("A"));
        assertEquals(Maybe.of(2), distinct.getValue("B"));
        assertEquals(Maybe.of(3), distinct.getValue("C"));
    }

}
