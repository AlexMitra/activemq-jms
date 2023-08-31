package pl.kempa.saska.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.ProcessedTicketDTO;

@Component
@Slf4j
public class ProcessedTicketReceiver {

	@JmsListener(destination = "temp-topic://" + "${spring.activemq.topic.ticket-processed}")
	public void onReceive(@Payload ProcessedTicketDTO processedTicketDTO,
	                      @Header(name = "jms_correlationId") String correlationId) {
		log.info("[CORRELATION ID {}] Ticket {} was processed with comment {}",
				correlationId, processedTicketDTO.getId(), processedTicketDTO.getComment());
	}
}
