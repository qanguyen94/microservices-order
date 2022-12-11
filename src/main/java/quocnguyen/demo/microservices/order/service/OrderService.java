package quocnguyen.demo.microservices.order.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quocnguyen.demo.microservices.order.entity.OrderTO;
import quocnguyen.demo.microservices.order.event_handler.OrderEventPublisher;
import quocnguyen.demo.microservices.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final CalculationService calculationService;
	private final PaymentService paymentService;
	private final OrderRepository orderRepository;
	private final OrderEventPublisher orderEventPublisher;

	public String handleOrder(OrderTO orderTO) {
		BigDecimal totalAmount = calculationService.calculate(orderTO.getPurchasedItems());
		orderTO.setTotalAmount(totalAmount);
		orderTO.setCurrency("VND");
		orderTO.setCreationTime(LocalDateTime.now());
		String orderId = orderRepository.create(orderTO);
		OrderTO createdOrderTO = orderRepository.get(orderId);
		paymentService.charge(createdOrderTO).ifPresentOrElse(
				paymentId -> orderEventPublisher.publish(createdOrderTO),
				() -> orderRepository.remove(orderId));
		return orderId;
	}

	public OrderTO get(String orderId) {
		return orderRepository.get(orderId);
	}

	public List<OrderTO> getAllOrders() {
		return orderRepository.getAllOrders();
	}
}
