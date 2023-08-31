package pl.kempa.saska.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessedTicketDTO {
	private String id;
	private TicketState state;
	private String comment;

	@JsonCreator
	public ProcessedTicketDTO(@JsonProperty("id") String id,
	                          @JsonProperty("state") TicketState state,
	                          @JsonProperty("comment") String comment) {
		this.id = id;
		this.state = state;
		this.comment = comment;
	}
}
