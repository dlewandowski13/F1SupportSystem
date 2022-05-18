package sri.s26462.f1supportsystem.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Random;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BolidParameterMessage {

    private static long idIndex = 0;

    public static long nextId(){
        return idIndex++;
    }

    private long id;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    private float engineTemp;
    private int tirePressure;
    private int oilPressure;
    private float oilTemp;

    public static float setRandTemp(){
        return new Random().nextFloat(0,100);
    }

    public static int setRandPressure(){
        return new Random().nextInt(0,100);
    }

}
