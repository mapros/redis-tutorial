package org.mapros.redis.datatype;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisKeyTest extends BaseTest {
    /**
     * check key exists
     *
     * @throws Exception ex
     */
    @Test
    public void testKeyExists() throws Exception {
        Assert.assertFalse(jedis.exists("hello"));
        jedis.set("hello", "world");
        Assert.assertTrue(jedis.exists("hello"));
        Assert.assertTrue("world".equals(jedis.get("hello")));
    }

    @Test
    public void testGetValByKeyPattern() throws Exception {
        Set<String> keys = jedis.keys("*");
        Assert.assertTrue(0 == keys.size());

        jedis.set("hello", "world");
        jedis.set("hello", "honey");
        keys = jedis.keys("*");
        Assert.assertEquals(1, keys.size());

        jedis.set("hello1", "honey");
        jedis.set("test", "honey");
        keys = jedis.keys("*");
        Assert.assertEquals(3, keys.size());

        keys = jedis.keys("hello*");
        Assert.assertEquals(2, keys.size());

        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void testDelKey() throws Exception {
        jedis.set("hello", "world");
        Long affected = jedis.del("hello");//return number of affected rows
        Assert.assertTrue(1 == affected);
    }

    @Test
    public void testKeyExpire() throws Exception {
        jedis.set("hello", "world");
        Assert.assertTrue(1 == jedis.expire("hello", 5));//if return 1,set success
        Thread.sleep(6*1000);
        Long seconds = jedis.ttl("hello");//remaining time to live in seconds of a key
        System.out.println("time to live in seconds:"+seconds);
        Assert.assertFalse(jedis.exists("hello"));
        Assert.assertEquals("none",jedis.type("hello"));// if key expire ,will return none
    }
}
