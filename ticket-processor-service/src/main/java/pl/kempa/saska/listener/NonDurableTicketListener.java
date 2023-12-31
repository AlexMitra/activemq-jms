package pl.kempa.saska.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.TicketDTO;

@Component
@Slf4j
public class NonDurableTicketListener {
	@JmsListener(containerFactory = "nonDurableListenerContainerFactory",
			destination = "${spring.activemq.topic.ticket-for-processing}")
	public void onReceive(TicketDTO ticketDTO) {
		log.info("[NON-DURABLE RECEIVER] Received ticket {}, begin processing ...", ticketDTO);
	}
}
