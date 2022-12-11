package quocnguyen.demo.microservices.order.rabbitmq;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import quocnguyen.demo.microservices.order.repository.OrderRepository;
import quocnguyen.demo.microservices.order.service.CalculationService;
import quocnguyen.demo.microservices.order.service.OrderService;
import quocnguyen.demo.microservices.order.service.PaymentServiceAPI;

@Slf4j
@Configuration
@Profile({"rabbitmq", "rabbitmq-k8s"}) // This publisher is available only when at least one of these profiles is active
public class RabbitMQConfiguration {

	private static final String TOPIC_EXCHANGE_NAME = "order";
	private static final String PUBLISHING_QUEUE = "order-created";
	private static final String LISTENING_QUEUE = "order-delivering";

	@PostConstruct
	void init() {
		log.info("Service is running with RabbitMQ integration");
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Queue queue() {
		return new Queue(PUBLISHING_QUEUE, false);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("order.created.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(LISTENING_QUEUE);
		return container;
	}

	@Bean
	OrderCreatedEventPublisher orderCreatedEventPublisher(RabbitTemplate rabbitTemplate) {
		return new OrderCreatedEventPublisher(rabbitTemplate);
	}

	@Bean
	OrderService orderService(CalculationService calculationService,
							  PaymentServiceAPI paymentService,
							  OrderRepository orderRepository,
							  OrderCreatedEventPublisher orderCreatedEventPublisher) {
		return new RabbitMQDemoOrderService(calculationService, paymentService, orderRepository, orderCreatedEventPublisher);
	}
}
