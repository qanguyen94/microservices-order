package quocnguyen.demo.microservices.order.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import quocnguyen.demo.microservices.order.entity.OrderTO;

@Slf4j
@RequiredArgsConstructor
public class OrderCreatedEventPublisher {

	private static final String TOPIC_EXCHANGE_NAME = "order";
	private static final String ORDER_CREATED_ROUTING_KEY = "order.created";

	private final RabbitTemplate rabbitTemplate;

	public void publish(OrderTO orderTO) {
		rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ORDER_CREATED_ROUTING_KEY, orderTO.getId());
		log.info("Order created event of the orderId {} has been published", orderTO.getId());
	}

}