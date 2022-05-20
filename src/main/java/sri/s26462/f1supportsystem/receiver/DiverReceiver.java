package sri.s26462.f1supportsystem.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.AlertMessageDto;
import sri.s26462.f1supportsystem.receiver.mapper.AlertMapper;

import javax.jms.Message;

@Component
@RequiredArgsConstructor
public class DiverReceiver {

    private final AlertMapper alertMapper;
    @JmsListener(destination = JmsConfig.DRIVER_ALERT, containerFactory = "queueConnectionFactory")
    public void receiveBolidAlert(@Payload AlertMessageDto convertedMessage,
                                      @Headers MessageHeaders messageHeaders,
                                      Message message) {
//        AlertDto alertDto = alertMapper.convertAlertToDto(convertedMessage);
        System.out.println("DiverReceiver.receiveBolidAlert, message: " + convertedMessage);
    }
}
