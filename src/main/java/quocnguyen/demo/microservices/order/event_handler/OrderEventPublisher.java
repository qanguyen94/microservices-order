package quocnguyen.demo.microservices.order.event_handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import quocnguyen.demo.microservices.order.entity.OrderTO;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

	private static final String TOPIC_EXCHANGE_NAME = "order";
	private static final String ORDER_RECEIVE_ROUTING_KEY = "order.received";

	private final RabbitTemplate rabbitTemplate;

	public void publish(OrderTO orderTO) {
		rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ORDER_RECEIVE_ROUTING_KEY, orderTO.getId());
		log.info("Order with id {} has been published", orderTO.getId());
	}

}