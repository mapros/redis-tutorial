package org.mapros.redis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * created by mapros on 2016-10-16.
 */
public class SubThread extends Thread {
    private final JedisPool jedisPool;
    private final JedisSubscriber subscriber = new JedisSubscriber();

    public SubThread(JedisPool jedisPool) {
        super("SubThread");
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        String channel = "mychannel";
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(subscriber, channel);
        } catch (Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}