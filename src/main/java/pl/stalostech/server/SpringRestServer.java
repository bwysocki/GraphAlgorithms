package pl.stalostech.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Embedded web server
 * @author Bartosz Wysocki
 */
@ComponentScan(basePackages = {"pl.stalostech"})
@EnableAutoConfiguration
public class SpringRestServer {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestServer.class, args);
    }

}
