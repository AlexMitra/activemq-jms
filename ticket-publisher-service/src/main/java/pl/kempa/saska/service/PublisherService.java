package pl.kempa.saska.service;

public interface PublisherService<T> {
	void send(T dto, String destination);

	void sendWithReply(T dto, String destination, String replyDestination);
}
