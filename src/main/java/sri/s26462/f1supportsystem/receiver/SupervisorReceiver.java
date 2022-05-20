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
import sri.s26462.f1supportsystem.model.EngineParameterDto;
import sri.s26462.f1supportsystem.receiver.checker.BolidParameterChecker;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SupervisorReceiver {

    private final JmsTemplate jmsTemplate;

    private final BolidParameterChecker bolidParameterChecker;

    @JmsListener(destination = JmsConfig.PITSTOP_REQUEST)
    public void pitstopReceive(@Payload EngineParameterDto convertedMessage,
                                            @Headers MessageHeaders messageHeaders,
                                            Message message) throws JMSException {
        Map<String, String> parameters = new HashMap<>();
        if (bolidParameterChecker.isCriticalEngineTemp(convertedMessage)){
            parameters.put("Engine temp: ", String.valueOf(convertedMessage.getEngineTemp()));
        }
        if (bolidParameterChecker.isCriticalOilTemp(convertedMessage)){
            parameters.put("Oil temp: ", String.valueOf(convertedMessage.getOilTemp()));
        }
        if (bolidParameterChecker.isCriticalOilPressure(convertedMessage)){
            parameters.put("Oil pressure: ", String.valueOf(convertedMessage.getOilPressure()));
        }
        if (bolidParameterChecker.isCriticalTirePressure(convertedMessage)){
            parameters.put("Tire pressure: ", String.valueOf(convertedMessage.getTirePressure()));
        }

        String reply;
        if(parameters.isEmpty()){
            reply = "There is no reason to get into pitstop.";
        } else {
            reply = "Come into pitstop";
        }

        System.out.println("SupervisorReceiver.pitstopReceive, message: " + convertedMessage);
        Destination replyTo = message.getJMSReplyTo();
        AlertMessageDto msg = AlertMessageDto.builder()
                .id(AlertMessageDto.nextId())
                .createdAt(LocalDateTime.now())
                .message(reply)
                .build();
        jmsTemplate.convertAndSend(replyTo, msg);
    }
}
