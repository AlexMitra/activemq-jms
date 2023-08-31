package pl.kempa.saska.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.ProcessedTicketDTO;
import pl.kempa.saska.dto.TicketDTO;
import pl.kempa.saska.dto.TicketState;

@Component
@Slf4j
public class DurableTicketListener {

	@JmsListener(containerFactory = "durableListenerContainerFactory",
			destination = "${spring.activemq.topic.ticket-for-processing}")
	public JmsResponse<Message<ProcessedTicketDTO>> onReceive(@Payload TicketDTO ticketDTO,
	                                                          @Header(name = "jms_replyTo") String replyToTopic,
	                                                          @Header(
			                                                          name = "jms_correlationId") String correlationId) {
		log.info("[DURABLE RECEIVER] Received ticket {}, begin processing ...", ticketDTO);
		var message =
				MessageBuilder.withPayload(new ProcessedTicketDTO(ticketDTO.getId(), TicketState.CLOSED, "Done"))
						.setHeader("jms_correlationId", correlationId)
						.build();
		log.info("[REPLY] Sending processed ticket {} with correlationId {} to topic {}",
				ticketDTO.getId(), correlationId, replyToTopic);
		return JmsResponse.forTopic(message, replyToTopic);
	}
}
