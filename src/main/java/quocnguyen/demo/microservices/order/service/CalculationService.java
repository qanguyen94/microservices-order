package quocnguyen.demo.microservices.order.service;

import java.math.BigDecimal;
import java.util.List;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import quocnguyen.demo.microservices.order.entity.PurchasedItemTO;

@Service
public class CalculationService {

	private final Long PRICE_PER_ITEM = 123L;

	public BigDecimal calculate(@NonNull List<PurchasedItemTO> purchasedItemTOS) {
		return purchasedItemTOS.stream()
				.map(PurchasedItemTO::getQuantity)
				.reduce(Integer::sum)
				.map(totalItems -> BigDecimal.valueOf(totalItems * PRICE_PER_ITEM))
				.orElse(BigDecimal.ZERO);
	}

}
