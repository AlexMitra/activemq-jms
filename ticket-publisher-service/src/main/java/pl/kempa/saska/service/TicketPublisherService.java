package pl.kempa.saska.service;

import java.util.UUID;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Destination;
import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.TicketDTO;

@Service
@Slf4j
public class TicketPublisherService {

	@Value("${spring.activemq.topic.ticket-for-processing}")
	private String topic;

	@Value("${spring.activemq.topic.ticket-processed}")
	private String replyToTopic;

	@Autowired private JmsTemplate jmsTemplate;

	public void sendTicketForProcessing(TicketDTO ticketDTO) {
		log.info("!!! Sending ticket for processing: {}", ticketDTO);
		jmsTemplate.convertAndSend(topic, ticketDTO, message -> {
			message.setJMSReplyTo(new ActiveMQTempTopic(replyToTopic));
			String correlationId = UUID.randomUUID().toString();
			message.setJMSCorrelationID(correlationId);
			return message;
		});
	}
}
