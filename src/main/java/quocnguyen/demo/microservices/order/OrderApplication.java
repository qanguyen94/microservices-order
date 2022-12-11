package quocnguyen.demo.microservices.order;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import quocnguyen.demo.microservices.order.config.WebSecurityConfiguration;
import quocnguyen.demo.microservices.order.external.payment.config.PaymentServiceConfig;

@Slf4j
@Import({PaymentServiceConfig.class,
//		RabbitMQConfiguration.class,
		WebSecurityConfiguration.class})
@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication application = new SpringApplication(OrderApplication.class);
		printStartupInfo(application.run(args).getEnvironment());
	}

	static void printStartupInfo(Environment environment) throws IOException {
		log.info("Connecting to payment service at: " + environment.getProperty("services.payment.url"));
	}

}
