package quocnguyen.demo.microservices.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

	public void notifyDeliveryStarted(String orderId) {
		log.info("Email sent: start delivering the order with id {}", orderId);
	}
}
