package be.rdhaese.packetdelivery.back_end.application;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.MailContentLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Robin D'Haese
 */
@SpringBootApplication(scanBasePackages = "be.rdhaese.packetdelivery.back_end")
@EnableCaching
public class App {

    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder(App.class)
                .build()
                .run(args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager();
    }

    @Bean(name = "deliveredMail")
    public Resource deliveredMail(){
        return new ClassPathResource("/mails/delivered.html");
    }

    @Bean(name = "departedMail")
    public Resource departedMail(){
        return new ClassPathResource("/mails/departed.html");
    }

    @Bean(name = "lostMail")
    public Resource lostMail(){
        return new ClassPathResource("/mails/lost.html");
    }

    @Bean(name = "notDeliveredMail")
    public Resource notDeliveredMail(){
        return new ClassPathResource("/mails/not_delivered.html");
    }
}