package pl.kempa.saska.service.impl;

import java.util.UUID;

import org.apache.activemq.command.ActiveMQTempTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.TicketDTO;
import pl.kempa.saska.service.PublisherWithReplyService;

@Service
@AllArgsConstructor
@Slf4j
public class TicketPublisherWithReplyService implements PublisherWithReplyService<TicketDTO> {

	private JmsTemplate jmsTemplate;

	@Override
	public void sendWithReply(TicketDTO ticketDTO, String destination, String replyDestination) {
		log.info("!!! Sending ticket for processing: {}", ticketDTO);
		jmsTemplate.convertAndSend(destination, ticketDTO, message -> {
			message.setJMSReplyTo(new ActiveMQTempTopic(replyDestination));
			String correlationId = UUID.randomUUID().toString();
			message.setJMSCorrelationID(correlationId);
			return message;
		});
	}
}
