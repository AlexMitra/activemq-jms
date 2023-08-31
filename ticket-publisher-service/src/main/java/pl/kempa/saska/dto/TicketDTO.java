package pl.kempa.saska.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO implements Serializable {
	private String id;
	private String title;
	private String description;
	private TicketState state;
}
