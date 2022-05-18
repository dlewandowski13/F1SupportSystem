package sri.s26462.f1supportsystem.receiver.receiverDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngineParameterDto {

    private Long id;
    private Long bolidId;
    private LocalDateTime createdAt;
    private float engineTemp;
    private int tirePressure;
    private int oilPressure;
    private float oilTemp;
}
