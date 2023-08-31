package pl.kempa.saska.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.TicketDTO;

@Component
@Slf4j
public class VirtualTopicListenerB {

	@JmsListener(destination = "Consumer.B.VirtualTopic." + "${spring.activemq.topic.virtual-topic}")
	public void onReceive(TicketDTO ticketDTO) {
		log.info("LISTENER [B] Received ticket {}", ticketDTO);
	}
}
