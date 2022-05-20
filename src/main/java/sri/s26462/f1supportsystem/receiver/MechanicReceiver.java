package sri.s26462.f1supportsystem.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.AlertMessageDto;

import javax.jms.Message;

@Component
@RequiredArgsConstructor
public class MechanicReceiver {

    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.MECHANIC_ALERT, containerFactory = "queueConnectionFactory")
    public void receiveBolidAlertToMechanic(@Payload AlertMessageDto convertedMessage,
                                  @Headers MessageHeaders messageHeaders,
                                  Message message) {

        System.out.println("MechanicReceiver.receiveBolidAlertToMechanic, message: " + convertedMessage);
    }
}
