package org.mapros.redis.datatype;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * created by mapros on 2016-10-16.
 */
public abstract class BaseTest {
    protected static JedisPool jPool;
    protected Jedis jedis;

    /**
     * init pool
     *
     * @throws Exception ex
     */
    @BeforeClass
    public static void init() throws Exception {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(5 * 1000);
        config.setTestOnBorrow(true);
        //create jedis pool
        jPool = new JedisPool(config, "127.0.0.1");
    }

    /**
     * clear all db in redis
     *
     * @throws Exception ex
     */
    @Before
    public void setUp() throws Exception {
        try {
            if (jPool != null) {
                jedis = jPool.getResource();
                System.out.println("response code:" + jedis.flushAll());
            } else {
                throw new Exception("init pool failed");
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @AfterClass
    public static void release() throws Exception {
        if (jPool != null) {
            jPool.close();
        }
    }
}
