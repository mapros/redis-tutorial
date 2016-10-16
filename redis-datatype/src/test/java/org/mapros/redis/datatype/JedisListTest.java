package org.mapros.redis.datatype;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.SortingParams;

import java.util.List;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisListTest extends BaseTest {
    @Test
    public void testLPush() throws Exception {
        jedis.lpush("languages","c","c#","java");
        jedis.lpush("languages","php");
        jedis.lpush("languages","python");
        jedis.lpush("numbers","0","1");
        jedis.lpush("numbers","0","1");
        jedis.lpush("numbers","2");
        List<String> languages = jedis.lrange("languages", 0, -1);
        for (String language : languages) {
            System.out.println(language);
        }
        List<String> numbers = jedis.lrange("numbers", 0, -1);
        for (String number : numbers) {
            System.out.println(number);
        }
    }

    @Test
    public void testLRem() throws Exception {
        jedis.lpush("languages","c","c#","java");
        jedis.lpush("languages","php");
        jedis.lpush("languages","python");
        jedis.lpush("languages","go","python");
        System.out.println(jedis.lrange("languages",0,-1));
        jedis.lrem("languages",2,"python");
        System.out.println(jedis.lrange("languages",0,-1));
    }

    @Test
    public void testLSet() throws Exception {
        jedis.lpush("languages","c","c#","java");
        jedis.lpush("languages","php");
        jedis.lpush("languages","python");
        jedis.lpush("languages","go","python");
        System.out.println(jedis.lrange("languages",0,-1));
        jedis.lset("languages",3,"ruby");
        System.out.println(jedis.lrange("languages",0,-1));
    }

    @Test
    public void testLLen() throws Exception {
        jedis.lpush("languages","c","c#","java");
        jedis.lpush("languages","php");
        jedis.lpush("languages","python");
        jedis.lpush("languages","go","python");
        Assert.assertTrue(7==jedis.llen("languages"));
    }

    /**
     * sort can only used for number
     * if not number,use sorting params(alpha) to fix it
     * @throws Exception ex
     */
    @Test
    public void testSort() throws Exception {
        jedis.lpush("numbers","0","1");
        jedis.lpush("numbers","0","1");
        jedis.lpush("numbers","2");
        System.out.println(jedis.sort("numbers"));
        System.out.println(jedis.lrange("numbers",0,jedis.llen("numbers")));
        jedis.lpush("languages","c","c#","java");
        jedis.lpush("languages","php");
        jedis.lpush("languages","python");
        jedis.lpush("languages","go","python");
        SortingParams sortingParams = new SortingParams();
        sortingParams.alpha();
        System.out.println(jedis.sort("languages",sortingParams));
        System.out.println(jedis.lrange("languages",0,jedis.llen("languages")));
        sortingParams.limit(0,3);
        System.out.println(jedis.sort("languages",sortingParams));
    }
}
