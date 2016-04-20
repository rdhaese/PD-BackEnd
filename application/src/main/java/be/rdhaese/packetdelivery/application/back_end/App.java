package be.rdhaese.packetdelivery.application.back_end;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created on 10/12/2015.
 *
 * @author Robin D'Haese
 */
@SpringBootApplication(scanBasePackages = "be.rdhaese.packetdelivery.back_end")
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .profiles("production")
                .build()
                .run(args);
    }
}