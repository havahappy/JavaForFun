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
  CommandLineRunner lookup(Sender sender) {
    return args -> {
      String topic = "test";
      String message = "example message";


      if (args.length > 0) {
        topic = args[0];
        message = args[1];
      }

      sender.sendMessage(topic, message);
    };
  }

}
