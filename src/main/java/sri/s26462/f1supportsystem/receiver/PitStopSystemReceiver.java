package sri.s26462.f1supportsystem.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import sri.s26462.f1supportsystem.receiver.mapper.EngineParameterMapper;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.BolidParameterMessage;
import sri.s26462.f1supportsystem.receiver.receiverModel.EngineParameter;
import sri.s26462.f1supportsystem.receiver.receiverRepo.EngineParameterRepository;

import javax.jms.Message;

@Component
@RequiredArgsConstructor
public class PitStopSystemReceiver {
    private final EngineParameterMapper engineParameterMapper;
    private final EngineParameterRepository engineParameterRepository;

    @JmsListener(destination = JmsConfig.TOPIC_BOLID_STATS, containerFactory = "topicConnectionFactory")
    public void receiveBolidParameter(@Payload BolidParameterMessage convertedMessage,
                                    @Headers MessageHeaders messageHeaders,
                                    Message message) {
        EngineParameter engineParameter = engineParameterMapper.convertBolidParameterMessageToEntity(convertedMessage);
        engineParameterRepository.save(engineParameter);
        System.out.println("PitStopSystemReceiver.receiveBolidParameter, message: " + convertedMessage);
    }
}
