package quocnguyen.demo.microservices.order.rabbitmq;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import quocnguyen.demo.microservices.order.entity.OrderTO;
import quocnguyen.demo.microservices.order.rabbitmq.OrderCreatedEventPublisher;
import quocnguyen.demo.microservices.order.repository.OrderRepository;
import quocnguyen.demo.microservices.order.service.CalculationService;
import quocnguyen.demo.microservices.order.service.OrderService;
import quocnguyen.demo.microservices.order.service.PaymentServiceAPI;

/**
 * This service will handle order and call to payment service synchronously
 * (services with RabbitMQ run asynchronously, this is just for demo, asynchronous communication will be present with delivery service). See @{@link OrderCreatedEventPublisher}
 */
@RequiredArgsConstructor
public class RabbitMQDemoOrderService implements OrderService {

	private final CalculationService calculationService;
	private final PaymentServiceAPI paymentService;
	private final OrderRepository orderRepository;
	private final OrderCreatedEventPublisher orderCreatedEventPublisher;

	@Override
	public String handleOrderRequest(OrderTO orderTO) {
		BigDecimal totalAmount = calculationService.calculate(orderTO.getPurchasedItems());
		orderTO.setTotalAmount(totalAmount);
		orderTO.setCurrency("VND");
		orderTO.setCreationTime(LocalDateTime.now());
		String orderId = orderRepository.create(orderTO);
		OrderTO createdOrderTO = orderRepository.get(orderId);
		paymentService.charge(createdOrderTO).ifPresentOrElse(
				paymentId -> orderCreatedEventPublisher.publish(createdOrderTO),
				() -> orderRepository.remove(orderId));
		return orderId;
	}

	@Override
	public OrderTO get(String orderId) {
		return orderRepository.get(orderId);
	}

	@Override
	public List<OrderTO> getAllOrders() {
		return orderRepository.getAllOrders();
	}
}
