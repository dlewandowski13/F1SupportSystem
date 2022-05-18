package sri.s26462.f1supportsystem.receiver.receiverModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngineParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long bolidId;
    private LocalDateTime createdAt;
    private float engineTemp;
    private int tirePressure;
    private int oilPressure;
    private float oilTemp;

}
