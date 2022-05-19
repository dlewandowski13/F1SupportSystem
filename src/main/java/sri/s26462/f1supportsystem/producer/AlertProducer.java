package sri.s26462.f1supportsystem.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.AlertMessage;
import sri.s26462.f1supportsystem.model.BolidParameterMessage;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AlertProducer {
    private final JmsTemplate jmsTemplate;

    public void sendDriverAlert(String message, String receiver) {
        AlertMessage alertMessage = AlertMessage.builder()
                .id(BolidParameterMessage.nextId())
                .createdAt(LocalDateTime.now())
                .message(message)
                .build();
        if (receiver.equals("driver")) {
            jmsTemplate.convertAndSend(JmsConfig.DRIVER_ALERT, alertMessage);
        } else if (receiver.equals("mechanic")) {
            jmsTemplate.convertAndSend(JmsConfig.MECHANIC_ALERT, alertMessage);
        }

        System.out.println("sendDriverAlert.sendDriverAlert to " + receiver
                + "  - sent message: " + alertMessage);
    }
}
