package kr.ac.kopo.ReadyToTravel.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheConfig {
    private Cache<String, String> cache;

    public CacheConfig() {
        cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    public void putValue(String key, String value) {
        cache.put(key, value);
    }

    public String getValue(String key) {
        return cache.getIfPresent(key);
    }
}
