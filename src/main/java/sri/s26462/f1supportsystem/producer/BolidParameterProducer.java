package sri.s26462.f1supportsystem.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.BolidParameterMessage;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BolidParameterProducer {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 15000)
    public void sendBolidParametersMessage() {
        BolidParameterMessage bolidParameterMessage = BolidParameterMessage.builder()
                .id(BolidParameterMessage.nextId())
                .createdAt(LocalDateTime.now())
                .engineTemp(BolidParameterMessage.setRandTemp())
                .tirePressure(BolidParameterMessage.setRandPressure())
                .oilPressure(BolidParameterMessage.setRandPressure())
                .oilTemp(BolidParameterMessage.setRandTemp())
                .build();
        jmsTemplate.convertAndSend(JmsConfig.QUEUE_BOLID_STATS, bolidParameterMessage);
        System.out.println("BolidParameterProducer.sendBolidParametersMessage - sent message: "
        + bolidParameterMessage);
    }
}
