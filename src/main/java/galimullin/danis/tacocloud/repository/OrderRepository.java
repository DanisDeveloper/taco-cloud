package galimullin.danis.tacocloud.repository;

import galimullin.danis.tacocloud.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
