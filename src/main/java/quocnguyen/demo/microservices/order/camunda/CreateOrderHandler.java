package quocnguyen.demo.microservices.order.camunda;

import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ExternalTaskSubscription("ProcessOrder")
public class CreateOrderHandler implements ExternalTaskHandler {

	@SneakyThrows
	@Override
	public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {


		// There is no specific handling yet when receiving the event from Camunda
		// Just write log to verify the integration with Camunda is successful
		log.info("Start creating a new order");

		VariableMap variables = Variables.createVariables();
		String orderId = UUID.randomUUID().toString();
		variables.put("orderId", orderId);

		// To be able to see the process running in Camunda. Let make the process stay here for a while
		Thread.sleep(5000);

		// complete the task
		externalTaskService.complete(externalTask, variables);
		log.info("The order has been created successfully with id {}", orderId);
	}

}
