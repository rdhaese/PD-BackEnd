package be.rdhaese.packetdelivery.back_end.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created on 10/12/2015.
 *
 * @author Robin D'Haese
 */
@SpringBootApplication(scanBasePackages = "be.rdhaese.packetdelivery.back_end")
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .build()
                .run(args);
    }
}