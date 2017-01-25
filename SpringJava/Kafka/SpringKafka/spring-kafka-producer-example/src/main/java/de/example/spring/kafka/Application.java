package de.example.spring.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Bean
  CommandLineRunner lookup(Receiver receiver) {
    return args -> {
      String sleepTime = "60000";


      if (args.length > 0) {
        sleepTime = args[0];
      }

      Thread.sleep(Long.valueOf(sleepTime));
    };
  }

}
