package galimullin.danis.tacocloud.service;

import galimullin.danis.tacocloud.model.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
