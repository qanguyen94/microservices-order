package quocnguyen.demo.microservices.order.camunda;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import quocnguyen.demo.microservices.order.entity.OrderTO;
import quocnguyen.demo.microservices.order.service.OrderService;

/**
 * This service will trigger an order process which runs asynchronously
 */
@RequiredArgsConstructor
public class CamundaDemoOrderService implements OrderService {

	OrderProcessTrigger orderProcessTrigger;

	@Override
	public String handleOrderRequest(OrderTO orderTO) {
		orderProcessTrigger.startProcess(orderTO);
		return "Order is on the way...";
	}

	@Override
	public OrderTO get(String orderId) {
		return null;
	}

	@Override
	public List<OrderTO> getAllOrders() {
		return null;
	}
}
