package quocnguyen.demo.microservices.order.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quocnguyen.demo.microservices.order.entity.OrderTO;
import quocnguyen.demo.microservices.order.service.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public String createOrder(@RequestBody OrderTO orderTO) {
		return orderService.handleOrderRequest(orderTO);
	}

	@GetMapping("/{orderId}")
	public OrderTO getOrder(@PathVariable String orderId) {
		return orderService.get(orderId);
	}

	@GetMapping
	public List<OrderTO> getOrders() {
		return orderService.getAllOrders();
	}

}
