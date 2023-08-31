package pl.kempa.saska.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO implements Serializable {
	private String id;
	private String title;
	private String description;
	private TicketState state;

	@JsonCreator
	public TicketDTO(@JsonProperty("id") String id,
	                 @JsonProperty("title") String title,
	                 @JsonProperty("description") String description,
	                 @JsonProperty("state") TicketState state) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.state = state;
	}
}
