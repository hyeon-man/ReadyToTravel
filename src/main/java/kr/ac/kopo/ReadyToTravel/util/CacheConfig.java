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
                .maximumSize(100) // 최대 크기 설정
                .expireAfterWrite(5, TimeUnit.MINUTES) // 최대 수명 5분으로 설정
                .build();
    }

    public void putValue(String key, String value) {
        cache.put(key, value);
    }

    public String getValue(String key) {
        return cache.getIfPresent(key);
    }
}
