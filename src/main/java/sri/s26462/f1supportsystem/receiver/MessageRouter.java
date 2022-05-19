package sri.s26462.f1supportsystem.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.producer.AlertProducer;
import sri.s26462.f1supportsystem.receiver.mapper.EngineParameterMapper;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.BolidParameterMessage;
import sri.s26462.f1supportsystem.receiver.receiverDto.EngineParameterDto;

import javax.jms.Message;
@Component
@RequiredArgsConstructor
public class MessageRouter {

    private final EngineParameterMapper engineParameterMapper;
    private final AlertProducer alertProducer;

    @JmsListener(destination = JmsConfig.TOPIC_BOLID_STATS, containerFactory = "topicConnectionFactory")
    public void receiveBolidParameter(@Payload BolidParameterMessage convertedMessage,
                                      @Headers MessageHeaders messageHeaders,
                                      Message message) {
        String receiveMessage;
        EngineParameterDto engineParameterDto = engineParameterMapper.convertBolidParameterMessageToDto(convertedMessage);
        System.out.println("MessageRouter.receiveBolidParameter, message: " + engineParameterDto);
        if(engineParameterDto.getEngineTemp() > 100) {
            receiveMessage = "The engine temperature is to high - " + engineParameterDto.getEngineTemp();
            alertProducer.sendDriverAlert(receiveMessage,"driver");
            if (engineParameterDto.getEngineTemp() > 850){
                alertProducer.sendDriverAlert(receiveMessage,"mechanic");
            }
        }

    }
}

