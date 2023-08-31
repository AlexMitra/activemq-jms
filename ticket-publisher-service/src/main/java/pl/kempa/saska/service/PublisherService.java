package pl.kempa.saska.service;

public interface PublisherService<T> {

	void send(T dto, String destination);
}
