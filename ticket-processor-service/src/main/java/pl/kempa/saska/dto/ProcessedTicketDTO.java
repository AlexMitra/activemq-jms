package pl.kempa.saska.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessedTicketDTO {
	private String id;
	private TicketState state;
	private String comment;
}
