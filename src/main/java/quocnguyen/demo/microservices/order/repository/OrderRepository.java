package quocnguyen.demo.microservices.order.repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import quocnguyen.demo.microservices.order.entity.OrderTO;
import quocnguyen.demo.microservices.order.entity.PurchasedItemTO;

@Repository
public class OrderRepository {

	private int currentId = 1;
	private final Map<String, OrderTO> repository = new HashMap<>();

	public OrderRepository() {
		for (; currentId <= 5; currentId++) {
			OrderTO initialOrder = createInitialOrder(currentId);
			repository.put(initialOrder.getId(), initialOrder);
		}
	}

	public String create(OrderTO orderTO) {
		currentId++;
		String orderId = "order-" + currentId;
		orderTO.setId(orderId);
		repository.put(orderId, orderTO);
		return orderId;
	}

	public void update(OrderTO orderTO) {
		repository.put(orderTO.getId(), orderTO);
	}

	public OrderTO get(String orderId) {
		return repository.get(orderId);
	}

	public List<OrderTO> getAllOrders() {
		return repository.keySet()
				.stream()
				.map(repository::get)
				.collect(Collectors.toList());
	}

	public OrderTO remove(String orderId) {
		return repository.remove(orderId);
	}

	private OrderTO createInitialOrder(int currentId) {
		return OrderTO.builder()
				.id("order-" + currentId)
				.userId("user-" + currentId)
				.purchasedItems(Collections.singletonList(PurchasedItemTO.builder()
						.id("item-" + currentId)
						.quantity(10 * currentId)
						.build()))
				.totalAmount(BigDecimal.valueOf(100L * currentId))
				.currency("VND")
				.build();
	}
}
