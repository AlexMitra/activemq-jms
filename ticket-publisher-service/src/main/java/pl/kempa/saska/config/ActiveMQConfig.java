package pl.kempa.saska.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class ActiveMQConfig {

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTargetType(MessageType.TEXT);
		messageConverter.setTypeIdPropertyName("_type");
		return messageConverter;
	}
}
