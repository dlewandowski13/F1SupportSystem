package sri.s26462.f1supportsystem.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.AlertMessage;
import sri.s26462.f1supportsystem.model.BolidParameterMessage;
import sri.s26462.f1supportsystem.receiver.mapper.EngineParameterMapper;
import sri.s26462.f1supportsystem.receiver.receiverRepo.EngineParameterRepository;

import javax.jms.Message;

@Component
@RequiredArgsConstructor
public class DiverReceiver {
    @JmsListener(destination = JmsConfig.DRIVER_ALERT, containerFactory = "queueConnectionFactory")
    public void receiveBolidAlert(@Payload AlertMessage convertedMessage,
                                      @Headers MessageHeaders messageHeaders,
                                      Message message) {

        System.out.println("DiverReceiver.receiveBolidAlert, message: " + convertedMessage);
    }
}
