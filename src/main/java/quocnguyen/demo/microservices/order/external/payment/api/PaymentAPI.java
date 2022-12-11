package quocnguyen.demo.microservices.order.external.payment.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import quocnguyen.demo.microservices.order.external.payment.config.PaymentServiceConfig;
import quocnguyen.demo.microservices.order.external.payment.entity.PaymentTO;

@Service
@RequiredArgsConstructor
public class PaymentAPI {

	private RestTemplate restTemplate;
	private PaymentServiceConfig paymentServiceConfig;

	@Autowired
	public PaymentAPI(RestTemplateBuilder restTemplateBuilder,
					  PaymentServiceConfig paymentServiceConfig) {
		this.restTemplate = restTemplateBuilder.build();
		this.paymentServiceConfig = paymentServiceConfig;
	}

	public String charge(PaymentTO paymentTO) {
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(paymentServiceConfig.getUrl() + "/payment", paymentTO, String.class);
		return responseEntity.getBody();
	}
}
