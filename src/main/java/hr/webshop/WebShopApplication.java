package hr.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class WebShopApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebShopApplication.class, args);

        try {
            Runtime.getRuntime().exec("open http://localhost:8080/");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
