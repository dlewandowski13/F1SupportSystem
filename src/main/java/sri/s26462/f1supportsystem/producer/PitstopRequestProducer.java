package sri.s26462.f1supportsystem.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.config.JmsConfig;
import sri.s26462.f1supportsystem.model.AlertMessageDto;
import sri.s26462.f1supportsystem.model.EngineParameterDto;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PitstopRequestProducer {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public void pitstopRequest() throws JMSException, JsonProcessingException {
        EngineParameterDto bolidParameterMessage = EngineParameterDto.builder()
                .id(EngineParameterDto.nextId())
                .createdAt(LocalDateTime.now())
                .engineTemp(EngineParameterDto.setRandTemp())
                .tirePressure(EngineParameterDto.setRandPressure())
                .oilPressure(EngineParameterDto.setRandPressure())
                .oilTemp(EngineParameterDto.setRandTemp())
                .build();
        TextMessage responseMessage = (TextMessage) jmsTemplate.sendAndReceive(JmsConfig.PITSTOP_REQUEST,
                new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage plainMessage = session.createTextMessage();
                        try {
                            plainMessage.setText(objectMapper.writeValueAsString(bolidParameterMessage));
                            plainMessage.setStringProperty("_type", EngineParameterDto.class.getName());
                            return plainMessage;
                        } catch (JsonProcessingException e) {
                            throw new JMSException("Conversion to json failed: " + e.getMessage());
                        }
                    }
                });
        String responseText = responseMessage.getText();
        EngineParameterDto responseConverted = objectMapper.readValue(responseText, EngineParameterDto.class);
        System.out.println("PitstopRequestProducer.pitstopRequest got response: "
                + responseText+"\n\tconvertedMessage: "
                + responseConverted);
    }
}
