package pl.stalostech.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Embedded web server
 * @author Bartosz Wysocki
 */
@Configuration
@ComponentScan(basePackages = {"pl.stalostech" })
@ImportResource("/neo4jconfig.xml")
@EnableAutoConfiguration
public class SpringRestServer {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestServer.class, args);
    }

}
