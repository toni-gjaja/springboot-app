package hr.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class WebShopApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(WebShopApplication.class, args);

        try {
            Runtime.getRuntime().exec("open http://localhost:8080/");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebShopApplication.class);
    }

}
