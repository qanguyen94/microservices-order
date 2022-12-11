package quocnguyen.demo.microservices.order.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PurchasedItemTO {
	private String id;
	private int quantity;
}
