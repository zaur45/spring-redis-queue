package org.zaur45.spring_redis_pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zaur45.spring_redis_pubsub.sub.Subscriber;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private Subscriber subscriber;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        subscriber.readMessage();
    }
}
