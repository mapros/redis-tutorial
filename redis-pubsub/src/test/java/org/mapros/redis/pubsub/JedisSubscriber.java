package org.mapros.redis.pubsub;

import redis.clients.jedis.JedisPubSub;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisSubscriber extends JedisPubSub {
    public JedisSubscriber() {
    }

    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));

    }
}
