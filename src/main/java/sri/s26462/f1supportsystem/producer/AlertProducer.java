package sri.s26462.f1supportsystem.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.AlertMessageDto;
import sri.s26462.f1supportsystem.model.EngineParameterDto;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AlertProducer {
    private final JmsTemplate jmsTemplate;

    public void sendDriverAlert(String message, String receiver) {
        AlertMessageDto alertMessageDto = AlertMessageDto.builder()
                .id(EngineParameterDto.nextId())
                .createdAt(LocalDateTime.now())
                .message(message)
                .build();
        if (receiver.equals("driver")) {
            jmsTemplate.convertAndSend(JmsConfig.DRIVER_ALERT, alertMessageDto);
        } else if (receiver.equals("mechanic")) {
            jmsTemplate.convertAndSend(JmsConfig.MECHANIC_ALERT, alertMessageDto);
        }

        System.out.println("sendDriverAlert.sendDriverAlert to " + receiver
                + "  - sent message: " + alertMessageDto);
    }
}
