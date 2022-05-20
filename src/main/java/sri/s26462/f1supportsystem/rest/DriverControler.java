package sri.s26462.f1supportsystem.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sri.s26462.f1supportsystem.producer.PitstopRequestProducer;

import javax.jms.JMSException;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverControler {
    private final PitstopRequestProducer pitstopRequestProducer;
        @PostMapping
    public ResponseEntity pitstopRequest(@Payload(required = false) String payload) throws JMSException, JsonProcessingException {
        pitstopRequestProducer.pitstopRequest();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
