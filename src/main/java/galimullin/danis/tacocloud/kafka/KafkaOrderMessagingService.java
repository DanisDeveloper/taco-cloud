package galimullin.danis.tacocloud.kafka;

import galimullin.danis.tacocloud.model.TacoOrder;
import galimullin.danis.tacocloud.service.OrderMessagingService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderMessagingService implements OrderMessagingService {
    KafkaTemplate<String, TacoOrder> kafkaTemplate;

    KafkaOrderMessagingService(KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        kafkaTemplate.send("orders", order);
    }
}
