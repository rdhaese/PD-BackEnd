package be.rdhaese.packetdelivery.back_end.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Robin D'Haese
 */
@SpringBootApplication(scanBasePackages = "be.rdhaese.packetdelivery.back_end")
@EnableCaching
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .build()
                .run(args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager();
    }
}