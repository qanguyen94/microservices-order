package quocnguyen.demo.microservices.order.camunda;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import quocnguyen.demo.microservices.order.service.OrderService;

@Slf4j
@Configuration
@Profile("camunda") // Beans initialized in this class are available only when this profile is active
public class CamundaConfiguration {

	@PostConstruct
	void init() {
		log.info("Service is running with Camunda integration");
	}

	@Bean
	OrderProcessTrigger orderProcessTrigger(RestTemplateBuilder restTemplateBuilder) {
		return new OrderProcessTrigger(restTemplateBuilder.build());
	}

	@Bean
	CreateOrderHandler createOrderHandler() {
		return new CreateOrderHandler();
	}

	@Bean
	OrderService orderService() {
		return new CamundaDemoOrderService();
	}
}
