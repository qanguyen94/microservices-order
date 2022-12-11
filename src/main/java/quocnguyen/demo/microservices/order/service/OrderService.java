package quocnguyen.demo.microservices.order.service;

import java.util.List;
import quocnguyen.demo.microservices.order.entity.OrderTO;

public interface OrderService {

	String handleOrderRequest(OrderTO orderTO);

	OrderTO get(String orderId);

	List<OrderTO> getAllOrders();

}
