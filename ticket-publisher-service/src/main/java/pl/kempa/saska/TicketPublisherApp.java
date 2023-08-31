package pl.kempa.saska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.kempa.saska.dto.TicketDTO;
import pl.kempa.saska.dto.TicketState;
import pl.kempa.saska.service.TicketPublisherService;

@SpringBootApplication
public class TicketPublisherApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TicketPublisherApp.class, args);
//		TicketPublisherService ticketPublisherService = context.getBean(TicketPublisherService.class);
//		ticketPublisherService.sendToTopic(new TicketDTO("CVM-6034", "Title", "Description", TicketState.IN_PROGRESS));
	}
}