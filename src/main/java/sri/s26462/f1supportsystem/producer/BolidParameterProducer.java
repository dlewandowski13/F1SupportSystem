package sri.s26462.f1supportsystem.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.EngineParameterDto;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BolidParameterProducer {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 15000)
    public void sendBolidParametersMessage() {
        EngineParameterDto bolidParameterMessage = EngineParameterDto.builder()
                .id(EngineParameterDto.nextId())
                .createdAt(LocalDateTime.now())
                .engineTemp(EngineParameterDto.setRandTemp())
                .tirePressure(EngineParameterDto.setRandPressure())
                .oilPressure(EngineParameterDto.setRandPressure())
                .oilTemp(EngineParameterDto.setRandTemp())
                .build();
        jmsTemplate.convertAndSend(JmsConfig.TOPIC_BOLID_STATS, bolidParameterMessage);
        System.out.println("BolidParameterProducer.sendBolidParametersMessage - sent message: "
        + bolidParameterMessage);
    }
}
