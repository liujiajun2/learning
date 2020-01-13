package xin.liujiajun.redis.client;

import cn.hutool.core.util.StrUtil;
import com.sun.istack.internal.NotNull;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.util.Pool;
import xin.liujiajun.redis.client.serialization.DefaultSerialication;
import xin.liujiajun.redis.client.serialization.Serialization;
import xin.liujiajun.redis.exception.RedisException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liujiajun
 * @date 2019-12-30 08:42
 **/
public class RedisClient {

    private String region;
    private Pool<Jedis> jedisPool;
    private Serialization serialization;

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_IF_EXIST = "XX";
    private static final String SET_EXPIRE_TIME_IN_EX = "EX";


    public RedisClient(String region, Pool<Jedis> jedisPool) {
        this(region, jedisPool, new DefaultSerialication());
    }

    public RedisClient(String region, Pool<Jedis> jedisPool, Serialization serialization) {
        if (jedisPool == null) {
            throw new RuntimeException("not init jedis pool");
        }
        this.region = region;
        this.jedisPool = jedisPool;
        if (serialization == null) {
            serialization = new DefaultSerialication();
        }
        this.serialization = serialization;
    }

    public String getRegion() {
        return region;
    }

    private String getKeyName(Object key) {
        if (key instanceof Number) {
            return region + ":I:" + key;
        }
        if (key instanceof String) {
            return region + ":S:" + key;
        }
        return region + ":O:" + key;

    }

    public Object get(Object key) throws RedisException {
        Object result = null;
        Jedis jedis = jedisPool.getResource();
        try {
            if (key == null) {
                return null;
            }
            String value = jedis.get(StrUtil.utf8Str(getKeyName(key)));
            if (value != null) {
                result = serialization.deserialize(value);
            }
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
        return result;
    }

    public void set(Object key, Object value) throws RedisException {
        if (key == null) {
            return;
        }
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(StrUtil.utf8Bytes(getKeyName(key)), serialization.serialize(value));
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
    }

    public String setex(@NotNull Object key, int seconds, Object value) throws RedisException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.setex(StrUtil.utf8Bytes(getKeyName(key)), seconds, serialization.serialize(value));
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }

    }

    public boolean lock(@NotNull String key, int seconds, Object value) throws RedisException {
        Jedis jedis = jedisPool.getResource();
        try {
            String ret = jedis.set(getKeyName(key), String.valueOf(value), SET_IF_NOT_EXIST, SET_EXPIRE_TIME_IN_EX, seconds);
            if ("OK".equals(ret)) {
                return true;
            }
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
        return false;
    }

    public boolean unlock(@NotNull Object key) throws RedisException {
        Jedis jedis = jedisPool.getResource();
        try {
            Long ret = jedis.del(getKeyName(key));
            if (ret != null && ret > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
        return false;
    }

    public void del(@NotNull Object key) throws RedisException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(getKeyName(key));
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
    }

    public void del(@NotNull List<Object> keys) throws RedisException {
        Jedis jedis = jedisPool.getResource();
        try {
            List<String> keyList = keys.stream().map(this::getKeyName).collect(Collectors.toList());
            jedis.del(keyList.toArray(new String[]{}));
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
    }

    public List<Object> lrangeLtrim(Object key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        List<Object> values = new ArrayList<>();
        try {
            if (key == null) {
                return values;
            }
            String ketName = StrUtil.utf8Str(getKeyName(key));
            jedis.watch(ketName);
            Transaction transaction = jedis.multi();
            transaction.lrange(ketName, start, end);
            transaction.ltrim(ketName, end + 1, -1);
            List<Object> result = transaction.exec();
            if (result != null && result.size() > 0) {
                List<byte[]> response = (List<byte[]>) result.get(0);
                for (byte[] val : response) {
                    values.add(serialization.deserialize(val));
                }
            }
            return values;
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
    }

    private byte[] getKey(Object key){
        return StrUtil.utf8Bytes(getKeyName(key));
    }

    public void hset(Object key, String field, Object value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hset(getKey(key), StrUtil.utf8Bytes(field), serialization.serialize(value));
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
    }


    public Map<String, Object> hgetAll(Object key) {

        Jedis jedis = jedisPool.getResource();

        try {

            Map<String, Object> retMap = new HashMap<>();
            Map<byte[], byte[]> map = jedis.hgetAll(getKey(key));
            if (map != null) {
                for (Map.Entry<byte[], byte[]> tmp : map.entrySet()) {
                    byte[] tmpKey = tmp.getKey();
                    String strKey = new String(tmpKey, StandardCharsets.UTF_8);
                    Object value = serialization.deserialize(tmp.getValue());
                    retMap.put(strKey, value);
                }
            }
            return retMap;
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
    }

    public void hdel(Object key, String field) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hdel(getKey(key), StrUtil.utf8Bytes(field));
        } catch (Exception e) {
            throw new RedisException();
        } finally {
            jedis.close();
        }
    }
}
