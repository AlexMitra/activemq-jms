package pl.kempa.saska.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.kempa.saska.dto.TicketDTO;
import pl.kempa.saska.service.PublisherService;
import pl.kempa.saska.service.PublisherWithReplyService;

@RestController
@RequestMapping(value = "/api/tickets")
public class TicketPublisherController {

	@Value("${spring.activemq.topic.ticket-for-processing}")
	private String topicForProcessing;

	@Value("${spring.activemq.topic.ticket-processed}")
	private String replyToTopic;

	@Value("${spring.activemq.topic.virtual-topic}")
	private String virtualTopic;

	@Autowired private PublisherWithReplyService publisherWithReplyService;
	@Autowired private PublisherService publisherService;

	@PostMapping
	public ResponseEntity<?> publish(@RequestBody TicketDTO ticketDTO, @RequestParam String action) {
		if ("PROCESS".equalsIgnoreCase(action)) {
			publisherWithReplyService.sendWithReply(ticketDTO, virtualTopic, replyToTopic);
		} else if ("MOVE".equalsIgnoreCase(action)) {
			publisherService.send(ticketDTO, virtualTopic);
		} else {
			return ResponseEntity.badRequest().body("incorrectAction");
		}
		return ResponseEntity.ok().build();
	}
}
