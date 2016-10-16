package org.mapros.redis.jedis;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisExampleTest {
    /**
     * use jedis add data
     *
     * @throws Exception ex
     */
    @Test
    public void testSetVal() throws Exception {
        //create a new jedis instance
        Jedis jedis = new Jedis();
        //set key-value in jedis
        jedis.set("username","mapros");
        //close connection
        jedis.close();
    }

    /**
     * get data from jedis
     * @throws Exception ex
     */
    @Test
    public void testGetVal() throws Exception {
        Jedis jedis =null;
        try  {
            jedis = new Jedis();
            Assert.assertEquals("equals username value", "mapros", jedis.get("username"));
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
