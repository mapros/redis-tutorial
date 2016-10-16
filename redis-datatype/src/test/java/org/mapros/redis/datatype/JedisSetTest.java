package org.mapros.redis.datatype;

import org.junit.Test;

import java.util.Set;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisSetTest extends BaseTest{
    /**
     * add set
     * @throws Exception ex
     */
    @Test
    public void testSAdd() throws Exception {
        jedis.sadd("keyset","key1","key2","key3");
        System.out.println(jedis.smembers("keyset"));
    }

    /**
     * remove set
     * @throws Exception ex
     */
    @Test
    public void testSRem() throws Exception {
        jedis.sadd("keyset","key1","key2","key3");
        System.out.println(jedis.smembers("keyset"));
        jedis.srem("keyset","key2","key3");
        System.out.println(jedis.smembers("keyset"));
    }

    /**
     * sinter
     * sunion
     *
     * @throws Exception ex
     */
    @Test
    public void testSetQuery() throws Exception {
        jedis.sadd("group1","key1","key2","key3");
        jedis.sadd("group2","key2","key3","key4");
        System.out.println("group1:"+jedis.smembers("group1"));
        System.out.println("group2:"+jedis.smembers("group2"));
        //sinter
        System.out.println("group1 inter group2:"+jedis.sinter("group1","group2"));
        //sinter store
        jedis.sinterstore("new","group1","group2");
        System.out.println("new group:"+jedis.smembers("new"));

        //sunion
        System.out.println("group1 union group2:"+jedis.sunion("group1","group2"));
        //sunion store
        jedis.sunionstore("newunion","group1","group2");
        System.out.println("new union group:"+jedis.smembers("newunion"));

        //sdiff
        System.out.println("group1 diff group2:"+jedis.sdiff("group1","group2"));
        jedis.sdiffstore("new diff","group1","group2");
        System.out.println("new diff group:"+jedis.smembers("new diff"));
    }
}
