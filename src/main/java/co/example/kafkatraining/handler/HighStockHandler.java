package co.example.kafkatraining.handler;

import co.example.kafkatraining.jpa.entity.ItemEntity;
import co.example.kafkatraining.jpa.repository.ItemRepository;
import co.example.kafkatraining.producers.HighStockProducer;
import co.example.kafkatraining.schemas.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HighStockHandler {

    private final ItemRepository repository;
    private final HighStockProducer highStockProducer;

    private Item convertToItem(ItemEntity itemEntity) {
        return new Item(itemEntity.getItemId(), itemEntity.getQuantity(), itemEntity.getValue());
    }

    @Scheduled(fixedRate = 36000000)
    public void checkHighStock() {
        List<ItemEntity> items = repository.findAll();
        items.stream()
                .filter(item -> item.getQuantity() > 300)
                .forEach(itemEntity -> {
                    itemEntity.applyOffer(itemEntity.getValue());
                    Item item = convertToItem(itemEntity);
                    highStockProducer.send(item);
                    log.info("Item {} has a high stock. Total Units: {}:", item.id(), item.quantity());
                });
    }
}
