package pl.kempa.saska.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.kempa.saska.dto.TicketDTO;
import pl.kempa.saska.service.impl.TicketPublisherService;

@RestController
@RequestMapping(value = "/api/tickets")
public class TicketPublisherController {

	@Value("${spring.activemq.topic.ticket-for-processing}")
	private String topic;

	@Value("${spring.activemq.topic.ticket-processed}")
	private String replyToTopic;

	@Autowired private TicketPublisherService publisherService;

	@PostMapping
	public ResponseEntity<Void> publish(@RequestBody TicketDTO ticketDTO) {
		publisherService.sendWithReply(ticketDTO, topic, replyToTopic);
		return ResponseEntity.ok().build();
	}
}
