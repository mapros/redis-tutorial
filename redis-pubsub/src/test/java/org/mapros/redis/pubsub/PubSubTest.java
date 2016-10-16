package org.mapros.redis.pubsub;

import org.junit.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * created by mapros on 2016-10-16.
 */
public class PubSubTest {

    public static void main(String[] args) throws Exception {
        //configuration
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);// max total connection 20
        config.setMaxIdle(5);//max idle connection 5
        config.setMaxWaitMillis(5 * 1000);//max wait 5s
        config.setTestOnBorrow(true);//redis server check
        //create jedis pool
        JedisPool jedisPool = new JedisPool(config, "localhost");
        SubThread subThread = new SubThread(jedisPool);
        subThread.start();

        Publisher publisher = new Publisher(jedisPool);
        publisher.start();

    }
}
