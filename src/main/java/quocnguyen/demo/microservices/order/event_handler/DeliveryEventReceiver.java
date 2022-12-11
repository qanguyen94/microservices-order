package quocnguyen.demo.microservices.order.event_handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quocnguyen.demo.microservices.order.service.NotificationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryEventReceiver {

	private final NotificationService notificationService;

	public void onEvent(String orderId) {
		notificationService.notifyDeliveryStarted(orderId);
	}

}