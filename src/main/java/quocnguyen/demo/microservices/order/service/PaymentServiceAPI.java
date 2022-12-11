package quocnguyen.demo.microservices.order.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quocnguyen.demo.microservices.order.entity.OrderTO;
import quocnguyen.demo.microservices.order.external.payment.entity.PaymentTO;
import quocnguyen.demo.microservices.order.external.payment.api.PaymentAPI;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceAPI {

	private final PaymentAPI paymentAPI;

	public Optional<String> charge(OrderTO orderTO) {
		PaymentTO paymentTO = toPaymentTO(orderTO);
		try {
			return Optional.ofNullable(paymentAPI.charge(paymentTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	private PaymentTO toPaymentTO(OrderTO orderTO) {
		return PaymentTO.builder()
				.userId(orderTO.getUserId())
				.orderId(orderTO.getId())
				.payedAmount(orderTO.getTotalAmount())
				.currency(orderTO.getCurrency())
				.build();
	}
}
