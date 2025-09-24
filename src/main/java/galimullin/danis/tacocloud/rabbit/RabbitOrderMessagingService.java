//package galimullin.danis.tacocloud.rabbit;
//
//import galimullin.danis.tacocloud.model.TacoOrder;
//import galimullin.danis.tacocloud.service.OrderMessagingService;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RabbitOrderMessagingService implements OrderMessagingService {
//    private RabbitTemplate rabbitTemplate;
//
//    RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Override
//    public void sendOrder(TacoOrder order) {
//        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
//        MessageProperties messageProperties = new MessageProperties();
//        Message message = messageConverter.toMessage(order, messageProperties);
//        rabbitTemplate.send("tacocloud", "tacocloud.order", message);
//    }
//}
//
