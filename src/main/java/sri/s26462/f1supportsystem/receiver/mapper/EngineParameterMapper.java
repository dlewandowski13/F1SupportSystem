package sri.s26462.f1supportsystem.receiver.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.model.BolidParameterMessage;
import sri.s26462.f1supportsystem.receiver.receiverDto.EngineParameterDto;
import sri.s26462.f1supportsystem.receiver.receiverModel.EngineParameter;

@Component
@AllArgsConstructor
public class EngineParameterMapper {
    private final ModelMapper modelMapper;

    public EngineParameterDto convertToDto(EngineParameter engineParameter) {
        return modelMapper.map(engineParameter, EngineParameterDto.class);
    }

    public EngineParameter convertToEntity(EngineParameterDto engineParameterDto) {
        return modelMapper.map(engineParameterDto, EngineParameter.class);
    }

    public EngineParameter convertBolidParameterMessageToEntity(BolidParameterMessage bolidParameterMessage) {
        return modelMapper.map(bolidParameterMessage, EngineParameter.class);
    }

    public EngineParameterDto convertBolidParameterMessageToDto(BolidParameterMessage bolidParameterMessage) {
        return modelMapper.map(bolidParameterMessage, EngineParameterDto.class);
    }


}
