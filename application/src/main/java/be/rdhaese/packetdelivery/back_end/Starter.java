package be.rdhaese.packetdelivery.back_end;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created on 10/12/2015.
 *
 * @author Robin D'Haese
 */
@SpringBootApplication
public class Starter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Starter.class)
                .profiles("production")
                .build()
                .run(args);
    }
}