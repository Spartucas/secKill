package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by guolei on 2018/1/11.
 */
public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
    public Seckill getSeckill(long seckillId) {
        //redis操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                //redis并没有实现内部序列化操作，存储的都是byte[]
                //采用自定义序列化,protostuff只能对有get set方法的pojo转化
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    //此时seckill已经被反序列化
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);

        }
        return null;

    }

    public String putSeckill(Seckill seckill) {
        //把对象序列化为byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:" + seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeOut = 60 * 60;//一小时
                String result = jedis.setex(key.getBytes(), timeOut, bytes);
                return result;
            }finally {
                jedis.close();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

}
