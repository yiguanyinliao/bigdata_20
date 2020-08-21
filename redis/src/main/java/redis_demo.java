import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class redis_demo {

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("123");
//        jedis.set("k100", "v100");
//        Set<String> keySet = jedis.keys("*");
//        for (String key : keySet) {
//            System.out.println(key);
//        }
//        Long exists = jedis.exists("k1", "k2", "k3");
//        System.out.println(exists);
//        jedis.close();


//        String pong = jedis.ping();
//        System.out.println("连接成功：+ "+ pong);
//        jedis.close();  // TODO 关闭连接

        Jedis jedis = getJedisFromPool();
        jedis.set("k11", "v11");
        jedis.close();  //TODO 从连接池中得到的jedis，close方法会将连接返还给连接池，而不是将连接关闭
    }

    // TODO 尽量使用连接池处理
    private static JedisPool jedisPool = null;
    public static Jedis getJedisFromPool() {
        if (jedisPool == null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10);  // 最大可用连接数
            jedisPoolConfig.setMaxIdle(5);  // 最大闲置连接数
            jedisPoolConfig.setMinIdle(5);  // 最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); // 连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000);  // 等待时间
            jedisPoolConfig.setTestOnBorrow(true);  // 取连接的时候进行一下测试 ping pong

            JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379,1000,"123");
            return jedisPool.getResource();
        }else{
            return jedisPool.getResource();
        }
    }

}
