package asl.model.core;

import org.junit.Assert;
import org.junit.Test;

public class QNameAtomTest {

    @Test
    public void testCreate() {
        // creation
        StringAtom key1_1 = new StringAtom("key1");
        QNameAtom name1 = QNameAtom.create(key1_1);
        Assert.assertNotNull("New qname weren't created!", name1);
        Assert.assertEquals("key1", name1.value());

        // same object
        StringAtom key1_2 = new StringAtom("key1");
        Assert.assertNotSame("same keys weren't expected!", key1_1, key1_2);
        QNameAtom name2 = QNameAtom.create(key1_2);
        Assert.assertSame("same keys were expected!", name1, name2);
    }

    @Test
    public void unequalCreation() {
        // different creation
        QNameAtom fromJavaString = QNameAtom.create("key1");
        QNameAtom fromASLString = QNameAtom.create(new StringAtom("key1"));
        Assert.assertNotEquals("unequal objects were expected!", fromJavaString, fromASLString);
    }
}