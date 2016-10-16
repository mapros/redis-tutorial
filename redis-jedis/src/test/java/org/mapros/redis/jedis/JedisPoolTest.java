package org.mapros.redis.jedis;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisPoolTest {
    private static JedisPool jPool;

    @BeforeClass
    public static void setUp() throws Exception {
        //configuration
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);// max total connection 20
        config.setMaxIdle(5);//max idle connection 5
        config.setMaxWaitMillis(5 * 1000);//max wait 5s
        config.setTestOnBorrow(true);//redis server check
        //create jedis pool
        jPool = new JedisPool(config, "localhost");
    }

    @AfterClass
    public static void release() throws Exception {
        if (jPool != null) {
            jPool.close();
        }
    }

    @Test
    public void testSetVal() throws Exception {
        Jedis jedis = jPool.getResource();
        jedis.set("username", "mapros");
        jedis.close();
    }

    @Test
    public void testGetVal() throws Exception {
        Jedis jedis = null;
        try {
            jedis = jPool.getResource();
            Assert.assertEquals("mapros", jedis.get("username"));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
