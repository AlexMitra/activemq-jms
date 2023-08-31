package pl.kempa.saska.service.impl;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kempa.saska.dto.TicketDTO;
import pl.kempa.saska.service.PublisherService;

@Service
@AllArgsConstructor
@Slf4j
public class VirtualTopicPublisherService implements PublisherService<TicketDTO> {

	private JmsTemplate jmsTemplate;

	@Override
	public void send(TicketDTO ticketDTO, String destination) {
		log.info("!!! Sending ticket to Virtual topic: {}", ticketDTO);
		jmsTemplate.convertAndSend(new ActiveMQTopic("VirtualTopic.".concat(destination)), ticketDTO);
	}
}
