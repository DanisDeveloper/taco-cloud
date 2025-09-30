package galimullin.danis.tacocloud.controller;

import galimullin.danis.tacocloud.config.OrderProps;
import galimullin.danis.tacocloud.integration.FileWriterGateway;
import galimullin.danis.tacocloud.model.TacoOrder;
import galimullin.danis.tacocloud.model.User;
import galimullin.danis.tacocloud.repository.OrderRepository;
import galimullin.danis.tacocloud.repository.UserRepository;
import galimullin.danis.tacocloud.service.OrderMessagingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps orderProps;
    private OrderMessagingService orderMessagingService;
    private FileWriterGateway  fileWriterGateway;

    public OrderController(OrderRepository orderRepository, OrderProps orderProps, OrderMessagingService orderMessagingService, FileWriterGateway fileWriterGateway) {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
        this.orderMessagingService = orderMessagingService;
        this.fileWriterGateway = fileWriterGateway;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Processing order: {}", tacoOrder);
        tacoOrder.setUser(user);
        orderRepository.save(tacoOrder);
        this.orderMessagingService.sendOrder(tacoOrder);
        this.fileWriterGateway.writeToFile("orders.log", tacoOrder.toString());
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }
}
