package pl.kempa.saska.service;

public interface PublisherWithReplyService<T> {

	void sendWithReply(T dto, String destination, String replyDestination);
}
