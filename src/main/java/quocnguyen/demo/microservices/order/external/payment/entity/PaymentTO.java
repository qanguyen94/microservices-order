package quocnguyen.demo.microservices.order.external.payment.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaymentTO {
	private String userId;
	private String orderId;
	private BigDecimal payedAmount;
	private String currency;
}
