package org.mapros.redis.datatype;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.Set;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisStringTest extends BaseTest {
    @Before
    public void initData() throws Exception {
        jedis.set("key1", "key1");
        jedis.set("key2", "key2");
        jedis.set("key3", "key3");
    }

    @Test
    public void testDelKey() throws Exception {
        if (jedis.exists("key1")) {
            Assert.assertTrue(1 == jedis.del("key1"));
            Assert.assertTrue(!jedis.exists("key1"));
        }
    }

    @Test
    public void testModifyKey() throws Exception {
        if (jedis.exists("key2")) {
            Assert.assertEquals("key2", jedis.get("key2"));
            jedis.set("key2", "modified");//set will override value
            Assert.assertEquals("modified", jedis.get("key2"));
            jedis.append("key2","+key2");
            Assert.assertEquals("modified+key2", jedis.get("key2"));//append to origin val
        }
    }

    @Test
    public void testQuery() throws Exception {
        Set<String> keys = jedis.keys("*");
        Assert.assertTrue(3==keys.size());
        for (String key : keys) {
            System.out.println(key);
        }
    }

    /**
     * set key if not exist
     * if existed ,will not set
     * @throws Exception ex
     */
    @Test
    public void testSetnx() throws Exception {
        Assert.assertTrue(0 ==jedis.setnx("key1","20"));
        Assert.assertFalse("20".equals(jedis.get("key1")));
    }

    @Test(expected = JedisDataException.class)
    public void testIncr() throws Exception {
        jedis.set("age","20");
        Assert.assertTrue(21==jedis.incr("age"));
        jedis.incr("key1");//if the value is not a number,it will throw JedisDataException
    }

    @Test
    public void testRange() throws Exception {
       Assert.assertEquals("ey",jedis.getrange("key1", 1, 2));
    }
}
