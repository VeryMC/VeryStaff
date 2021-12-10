package fr.verymc.Redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

public class RedisAccess {
    public static RedisAccess INSTANCE;
    private RedissonClient redissonClient;

    public RedisAccess(RedisCredentials credentials) {
        INSTANCE = this;
        this.redissonClient = this.initRedisson(credentials);
    }

    public static void init() {
        new RedisAccess(new RedisCredentials("redis", "Vf10OpOudxn8Z6x"));
    }
    public static void close() {
        RedisAccess.INSTANCE.getRedissonClient().shutdown();
    }

    private RedissonClient initRedisson(RedisCredentials credentials) {
        final Config redissonConfig = new Config();
        redissonConfig.setCodec(new JsonJacksonCodec());
        redissonConfig.setThreads(10);
        redissonConfig.setNettyThreads(10);
        redissonConfig.useSingleServer()
                .setAddress(credentials.getAddress())
                .setPassword(credentials.getPassword())
                .setClientName(credentials.getClientName());
        return Redisson.create(redissonConfig);
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }
}