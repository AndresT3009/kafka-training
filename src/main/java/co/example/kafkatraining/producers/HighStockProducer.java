package co.example.kafkatraining.producers;

import co.example.kafkatraining.schemas.Item;
import co.example.kafkatraining.schemas.LowStock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class HighStockProducer {

    private static final String TOPIC_NAME = "HIGH_STOCK_INVENTORY";
    private final KafkaTemplate<String, Item> kafkaTemplate;

    public void send(Item item) {


            kafkaTemplate.send(TOPIC_NAME, item.id(), item);
            log.info("Sent item: {} to topic: {}", item, TOPIC_NAME);


    }
}
