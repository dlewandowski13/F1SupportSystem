package sri.s26462.f1supportsystem.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.producer.AlertProducer;
import sri.s26462.f1supportsystem.receiver.checker.BolidParameterChecker;
import sri.s26462.f1supportsystem.receiver.mapper.EngineParameterMapper;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.EngineParameterDto;
import sri.s26462.f1supportsystem.receiver.receiverDto.ReceiverEngineParameterDto;

import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MessageRouter {

    private final EngineParameterMapper engineParameterMapper;
    private final AlertProducer alertProducer;
    private final BolidParameterChecker bolidParameterChecker;

    @JmsListener(destination = JmsConfig.TOPIC_BOLID_STATS, containerFactory = "topicConnectionFactory")
    public void receiveBolidParameter(@Payload EngineParameterDto convertedMessage,
                                      @Headers MessageHeaders messageHeaders,
                                      Message message) {
        String receiveMessage;
        ReceiverEngineParameterDto receiverEngineParameterDto = engineParameterMapper.convertBolidParameterMessageToDto(convertedMessage);
        System.out.println("MessageRouter.receiveBolidParameter, message: " + receiverEngineParameterDto);

        Map<String, String> parametersDangerous = new HashMap<>();
        if (bolidParameterChecker.isDangerousEngineTemp(convertedMessage)){
            parametersDangerous.put("Dangerous engine temp level: ", String.valueOf(convertedMessage.getEngineTemp()));
        }
        if (bolidParameterChecker.isDangerousOilTemp(convertedMessage)){
            parametersDangerous.put("Dangerous oil temp level: ", String.valueOf(convertedMessage.getOilTemp()));
        }
        if (bolidParameterChecker.isDangerousOilPressure(convertedMessage)){
            parametersDangerous.put("Dangerous oil pressure level: ", String.valueOf(convertedMessage.getOilPressure()));
        }
        if (bolidParameterChecker.isDangerousTirePressure(convertedMessage)){
            parametersDangerous.put("Dangerous tire pressure level: ", String.valueOf(convertedMessage.getTirePressure()));
        }

        Map<String, String> parametersCritical = new HashMap<>();
        if (bolidParameterChecker.isCriticalEngineTemp(convertedMessage)){
            parametersCritical.put("Critical engine temp level: ", String.valueOf(convertedMessage.getEngineTemp()));
        }
        if (bolidParameterChecker.isCriticalOilTemp(convertedMessage)){
            parametersCritical.put("Critical oil temp level: ", String.valueOf(convertedMessage.getOilTemp()));
        }
        if (bolidParameterChecker.isCriticalOilPressure(convertedMessage)){
            parametersCritical.put("Critical oil pressure level: ", String.valueOf(convertedMessage.getOilPressure()));
        }
        if (bolidParameterChecker.isCriticalTirePressure(convertedMessage)){
            parametersCritical.put("Critical tire pressure level: ", String.valueOf(convertedMessage.getTirePressure()));
        }


        if(!parametersDangerous.isEmpty()) {
            receiveMessage = "There is some dangerous parameters: " + parametersDangerous;
            alertProducer.sendDriverAlert(receiveMessage, "driver");
        }
        if (!parametersCritical.isEmpty()){
            receiveMessage = "There is some critical parameters: " + parametersCritical;
            alertProducer.sendDriverAlert(receiveMessage, "driver");
        }

    }
}

