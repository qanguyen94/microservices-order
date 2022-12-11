package quocnguyen.demo.microservices.order.external.payment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "services.payment")
public class PaymentServiceConfig {

	private String url;

}
