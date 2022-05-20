package sri.s26462.f1supportsystem.receiver.checker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.model.EngineParameterDto;

@Component
@RequiredArgsConstructor
public class BolidParameterChecker {

    public boolean isDangerousEngineTemp(EngineParameterDto engineParameterDto){
        if(engineParameterDto.getEngineTemp() > 400){
            return true;
        }
        return false;
    }

    public boolean isDangerousOilTemp(EngineParameterDto engineParameterDto){
        if(engineParameterDto.getOilTemp() > 400){
            return true;
        }
        return false;
    }

    public boolean isDangerousOilPressure(EngineParameterDto engineParameterDto){
        if(engineParameterDto.getOilPressure() > 20){
            return true;
        }
        return false;
    }

    public boolean isDangerousTirePressure(EngineParameterDto engineParameterDto){
        if(engineParameterDto.getOilPressure() <= 15){
            return true;
        }
        return false;
    }

    public boolean isCriticalEngineTemp(EngineParameterDto engineParameterDto){
        if( engineParameterDto.getEngineTemp() > 600){
            return true;
        }
        return false;
    }

    public boolean isCriticalOilTemp(EngineParameterDto engineParameterDto){
        if(engineParameterDto.getOilTemp() > 600){
            return true;
        }
        return false;
    }

    public boolean isCriticalOilPressure(EngineParameterDto engineParameterDto){
        if(engineParameterDto.getOilPressure() > 25){
            return true;
        }
        return false;
    }

    public boolean isCriticalTirePressure(EngineParameterDto engineParameterDto){
        if(engineParameterDto.getOilPressure() <= 10){
            return true;
        }
        return false;
    }
}
