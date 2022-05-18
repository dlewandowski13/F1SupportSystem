package sri.s26462.f1supportsystem.config;

import lombok.Builder;
import org.apache.activemq.artemis.spi.core.protocol.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
public class JmsConfig {

    public static final String QUEUE_BOLID_STATS = "BOLID_STATS.QUEUE";
    public static final String TOPIC_BOLID_STATS = "BOLID_STATS.TOPIC";
    public static final String PITSTOP_REQUEST = "PITSTOP_REQUEST.QUEUE";

    @Bean
    public JmsListenerContainerFactory<?>
    queueConnectionFactory(@Qualifier("jmsConnectionFactory")ConnectionFactory connectionFactory,
                           DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?>
    topicConnectionFactory(@Qualifier("jmsConnectionFactory")ConnectionFactory connectionFactory,
                           DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }


    @Bean
    public DynamicDestinationResolver destinationResolver() {
        return new DynamicDestinationResolver() {
            @Override
            public Destination resolveDestinationName (Session session, String destinationName,
                                                       boolean pubSubDomain) throws JMSException {
                if (destinationName.endsWith(".TOPIC")) {
                    pubSubDomain = true;
                }
                return super.resolveDestinationName(session, destinationName, pubSubDomain);
            }

        };
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new
                MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}