//package galimullin.danis.tacocloud.rabbit;
//
//import galimullin.danis.tacocloud.model.TacoOrder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class RabbitOrderReciever {
//    private RabbitTemplate rabbitTemplate;
//    private MessageConverter messageConverter;
//
//    RabbitOrderReciever(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.messageConverter = this.rabbitTemplate.getMessageConverter();
//    }
//
//    public TacoOrder recieve() {
//        Message message = rabbitTemplate.receive("orders");
//        return (message == null)
//                ? null
//                : (TacoOrder) this.messageConverter.fromMessage(message);
//    }
//
//    @RabbitListener(queues = "orders")
//    public void listener(TacoOrder tacoOrder) {
//        log.info("Recieved order: {}", tacoOrder);
//    }
//}
