package pl.kempa.saska;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
public class ActiveMQConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Value("${spring.activemq.username}")
	private String user;

	@Value("${spring.activemq.password}")
	private String password;

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTargetType(MessageType.TEXT);
		messageConverter.setTypeIdPropertyName("_type");
		return messageConverter;
	}

	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password,
				brokerUrl);
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(factory);
		cachingConnectionFactory.setClientId("myClientId");
		cachingConnectionFactory.setSessionCacheSize(100);
		return cachingConnectionFactory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory durableListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(cachingConnectionFactory());
		factory.setMessageConverter(jacksonJmsMessageConverter());
		factory.setConcurrency("1-1");
		factory.setClientId("myClientId");
		factory.setPubSubDomain(true);
		factory.setSubscriptionDurable(true);
		return factory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory nonDurableListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(cachingConnectionFactory());
		factory.setMessageConverter(jacksonJmsMessageConverter());
		factory.setConcurrency("1-1");
		factory.setClientId("myClientId");
		factory.setPubSubDomain(true);
		return factory;
	}
}
