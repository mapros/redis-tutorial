package org.mapros.redis.datatype;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * created by mapros on 2016-10-16.
 */
public class BatchStingTest extends BaseTest{
    @Test
    public void testMset() throws Exception {
        Assert.assertEquals("OK",jedis.mset("key1", "key1val", "key2", "key2val", "key3", "key3val"));
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key+":"+jedis.get(key));
        }
    }

    @Test
    public void testMget() throws Exception {
        Assert.assertEquals("OK",jedis.mset("key1", "key1val", "key2", "key2val", "key3", "key3val"));
        List<String> values = jedis.mget("key1" ,"key2","key3");
        for (String val : values) {
            System.out.println(val);
        }
    }
}
