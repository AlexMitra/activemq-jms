package pl.kempa.saska.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.TicketDTO;

@Service
@Slf4j
public class NonDurableTicketReceiver {
	@JmsListener(containerFactory = "nonDurableListenerContainerFactory",
			destination = "jms.topic.ticketForProcessing")
	public void receive(TicketDTO ticketDTO) {
		log.info("[NON-DURABLE RECEIVER] Received ticket {}, begin processing ...", ticketDTO);
	}
}
