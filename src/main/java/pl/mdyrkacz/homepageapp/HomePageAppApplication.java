package pl.mdyrkacz.homepageapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HomePageAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomePageAppApplication.class, args);
    }

}
