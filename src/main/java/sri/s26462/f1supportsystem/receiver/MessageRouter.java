package sri.s26462.f1supportsystem.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.receiver.mapper.EngineParameterMapper;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.BolidParameterMessage;
import sri.s26462.f1supportsystem.receiver.receiverDto.EngineParameterDto;

import javax.jms.Message;
@Component
@RequiredArgsConstructor
public class MessageRouter {

    private final EngineParameterMapper engineParameterMapper;

    @JmsListener(destination = JmsConfig.QUEUE_BOLID_STATS, containerFactory = "queueConnectionFactory")
    public void receiveBolidParameter(@Payload BolidParameterMessage convertedMessage,
                                      @Headers MessageHeaders messageHeaders,
                                      Message message) {
        EngineParameterDto engineParameterDto = engineParameterMapper.convertBolidParameterMessageToDto(convertedMessage);
        System.out.println("MessageRouter.receiveBolidParameter, message: " + engineParameterDto);
    }
}

