package sri.s26462.f1supportsystem.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.AlertMessage;

import javax.jms.Message;

public class MechanicReceiver {
    @JmsListener(destination = JmsConfig.MECHANIC_ALERT, containerFactory = "queueConnectionFactory")
    public void receiveBolidAlertToMechanic(@Payload AlertMessage convertedMessage,
                                  @Headers MessageHeaders messageHeaders,
                                  Message message) {

        System.out.println("MechanicReceiver.receiveBolidAlertToMechanic, message: " + convertedMessage);
    }
}
