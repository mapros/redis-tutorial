import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * created by mapros on 2016-10-16.
 */
public class JedisTransactionTest {
    @Test
    public void testTrans() throws Exception {
        Jedis jedis = new Jedis();
        jedis.flushAll();
        jedis.set("name", "mapros");
        jedis.set("age", "18");
        //enable transaction
        Transaction trans = jedis.multi();
        trans.incr("name");
        trans.incr("age");
        System.out.println(trans.exec());
        jedis.close();
    }
}
