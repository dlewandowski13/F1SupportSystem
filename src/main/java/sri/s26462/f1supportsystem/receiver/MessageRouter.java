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
import sri.s26462.f1supportsystem.model.EngineParameterDto;

import javax.jms.Message;
@Component
@RequiredArgsConstructor
public class MessageRouter {

    private final EngineParameterMapper engineParameterMapper;
    private final AlertProducer alertProducer;

    @JmsListener(destination = JmsConfig.TOPIC_BOLID_STATS, containerFactory = "topicConnectionFactory")
    public void receiveBolidParameter(@Payload EngineParameterDto convertedMessage,
                                      @Headers MessageHeaders messageHeaders,
                                      Message message) {
        String receiveMessage;
        sri.s26462.f1supportsystem.receiver.receiverDto.EngineParameterDto engineParameterDto = engineParameterMapper.convertBolidParameterMessageToDto(convertedMessage);
        System.out.println("MessageRouter.receiveBolidParameter, message: " + engineParameterDto);
        //TODO Check rest parameters
        if(engineParameterDto.getEngineTemp() > 100) {
            receiveMessage = "The engine temperature is to high - " + engineParameterDto.getEngineTemp();
            alertProducer.sendDriverAlert(receiveMessage,"driver");
            if (engineParameterDto.getEngineTemp() > 600){
                alertProducer.sendDriverAlert(receiveMessage,"mechanic");
            }
        }

    }
}

