package galimullin.danis.tacocloud.repository;

import galimullin.danis.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
