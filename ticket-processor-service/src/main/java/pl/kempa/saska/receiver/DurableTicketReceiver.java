package pl.kempa.saska.receiver;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.ProcessedTicketDTO;
import pl.kempa.saska.dto.TicketDTO;
import pl.kempa.saska.dto.TicketState;

@Service
@Slf4j
public class DurableTicketReceiver {

	@JmsListener(containerFactory = "durableListenerContainerFactory",
			destination = "jms.topic.ticketForProcessing")
	public JmsResponse<Message<ProcessedTicketDTO>> receive(@Payload TicketDTO ticketDTO,
	                                                        @Header(name = "jms_replyTo") String replyToTopic,
	                                                        @Header(name = "jms_correlationId") String correlationId) {
		log.info("[DURABLE RECEIVER] Received ticket {}, begin processing ...", ticketDTO);
		var message = MessageBuilder
				.withPayload(new ProcessedTicketDTO(ticketDTO.getId(), TicketState.CLOSED, "Done"))
				.setHeader("jms_correlationId", correlationId)
				.build();
		log.info("[DURABLE RECEIVER] Sending processed ticket {} with correlationId {} to topic {}",
				ticketDTO.getId(), correlationId, replyToTopic);
		return JmsResponse.forTopic(message, replyToTopic);
	}
}
