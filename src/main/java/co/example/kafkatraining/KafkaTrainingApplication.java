package co.example.kafkatraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTrainingApplication.class, args);
    }

}
