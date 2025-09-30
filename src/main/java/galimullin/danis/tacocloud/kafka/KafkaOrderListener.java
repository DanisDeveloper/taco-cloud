//package galimullin.danis.tacocloud.kafka;
//
//import galimullin.danis.tacocloud.model.TacoOrder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class KafkaOrderListener {
//
//    @KafkaListener(topics = "orders", groupId = "taco-order-group")
//    public void listen(TacoOrder order) {
//        log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + order);
//    }
//}
