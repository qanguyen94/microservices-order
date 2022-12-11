package quocnguyen.demo.microservices.order.camunda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import quocnguyen.demo.microservices.order.entity.OrderTO;

@Slf4j
@RequiredArgsConstructor
public class OrderProcessTrigger {

	private final RestTemplate restTemplate;

	@Autowired
	public OrderProcessTrigger(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public void startProcess(OrderTO orderTO) {
		log.info("Receive order request. Start order process...");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>("" ,headers);
		restTemplate.postForEntity("http://localhost:9090/engine-rest/process-definition/key/OrderProcess/start", entity, Object.class);
		log.info("Order process has been started!");
	}

}
