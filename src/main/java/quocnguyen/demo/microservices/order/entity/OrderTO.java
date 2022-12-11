package quocnguyen.demo.microservices.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderTO {
	private String id;
	private String userId;
	private List<PurchasedItemTO> purchasedItems;
	private BigDecimal totalAmount;
	private String currency;
	private LocalDateTime creationTime;
}
