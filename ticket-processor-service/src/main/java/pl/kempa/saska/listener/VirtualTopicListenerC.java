package pl.kempa.saska.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.TicketDTO;

@Component
@Slf4j
public class VirtualTopicListenerC {

	@JmsListener(destination = "Consumer.C.VirtualTopic." + "${spring.activemq.topic.virtual-topic}")
	public void onReceive(TicketDTO ticketDTO) {
		log.info("LISTENER [C] Received ticket {}", ticketDTO);
	}
}
