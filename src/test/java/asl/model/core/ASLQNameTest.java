package asl.model.core;

import org.junit.Assert;
import org.junit.Test;

public class ASLQNameTest {

    @Test
    public void testCreate() {
        // simple creation
        ASLString key1_1 = new ASLString("key1");
        ASLQName name1 = ASLQName.create(key1_1);
        Assert.assertNotNull("New qname weren't created!", name1);
        Assert.assertEquals("key1", name1.value());

        // same object
        ASLString key1_2 = new ASLString("key1");
        Assert.assertNotSame("same keys weren't expected!", key1_1, key1_2);
        ASLQName name2 = ASLQName.create(key1_2);
        Assert.assertSame("same keys were expected!", name1, name2);
    }
}