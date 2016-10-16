package org.mapros.redis.datatype;

import org.junit.Assert;
import org.junit.Test;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisHashTest extends BaseTest{
    /**
     * set hash to redis
     * get
     * @throws Exception ex
     */
    @Test
    public void testHash() throws Exception {
        //set hash object
        jedis.hset("user","username","mapros");
        jedis.hset("user","age","26");
        jedis.hset("user","address","cd");
        jedis.hset("user","phonenumber","13219681338");
        //hash get all
        System.out.println(jedis.hgetAll("user"));
        //hash get field
        System.out.println(jedis.hget("user","phonenumber"));
    }

    @Test
    public void testHDel() throws Exception {
        //set hash object
        jedis.hset("user","username","mapros");
        jedis.hset("user","age","26");
        jedis.hset("user","address","cd");
        jedis.hset("user","phonenumber","13219681338");
        //delete
        jedis.hdel("user","username","age");
        System.out.println(jedis.hgetAll("user"));
    }
    @Test
    public void testHIncrBy() throws Exception {
        jedis.hset("user","age","26");
        Assert.assertTrue(28==jedis.hincrBy("user","age",2));
        Assert.assertTrue(2==jedis.hincrBy("user","num",2));
    }

    /**
     * hkeys : get all keys in hash
     * hvals : get all value in hash
     * hlen : get number of keys
     * @throws Exception ex
     */
    @Test
    public void teshHashQuery() throws Exception {
        //set hash object
        jedis.hset("user","username","mapros");
        jedis.hset("user","age","26");
        jedis.hset("user","address","cd");
        jedis.hset("user","phonenumber","13219681338");
        //hkeys
        System.out.println(jedis.hkeys("user"));
        Assert.assertTrue(4==jedis.hkeys("user").size());
        //hvals
        System.out.println(jedis.hvals("user"));
        Assert.assertTrue(4==jedis.hvals("user").size());
        //hlen
        Assert.assertTrue(4==jedis.hlen("user"));
    }
}
