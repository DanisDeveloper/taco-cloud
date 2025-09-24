package galimullin.danis.tacocloud.controller;

import galimullin.danis.tacocloud.model.TacoOrder;
import galimullin.danis.tacocloud.repository.OrderRepository;
import galimullin.danis.tacocloud.service.OrderMessagingService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/orders", produces = "application/json")
@CrossOrigin("http://localhost:8080")
public class OrderRestController {

    private OrderRepository orderRepository;
    private OrderMessagingService orderMessagingService;
    public OrderRestController(OrderRepository orderRepository, OrderMessagingService orderMessagingService) {
        this.orderMessagingService = orderMessagingService;
        this.orderRepository = orderRepository;
    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder createOrder(@RequestBody TacoOrder order) {
        this.orderMessagingService.sendOrder(order);
        return this.orderRepository.save(order);
    }

    // Лучше в отдельный контроллер для заказов
    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody TacoOrder order) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody TacoOrder patch
    ) {
        TacoOrder order = orderRepository.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping(value = "/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException exception) {}
    }
}
