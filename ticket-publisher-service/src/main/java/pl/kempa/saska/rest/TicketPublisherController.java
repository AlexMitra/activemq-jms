package pl.kempa.saska.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pl.kempa.saska.dto.TicketDTO;
import pl.kempa.saska.service.TicketPublisherService;

@RestController
@RequestMapping(value = "/api/tickets")
@AllArgsConstructor
public class TicketPublisherController {

	private TicketPublisherService publisherService;

	@PostMapping
	public ResponseEntity<Void> publish(@RequestBody TicketDTO ticketDTO) {
		publisherService.sendTicketForProcessing(ticketDTO);
		return ResponseEntity.ok().build();
	}
}
