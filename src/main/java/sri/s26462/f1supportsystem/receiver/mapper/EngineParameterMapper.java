package sri.s26462.f1supportsystem.receiver.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.model.EngineParameterDto;
import sri.s26462.f1supportsystem.receiver.receiverDto.ReceiverEngineParameterDto;
import sri.s26462.f1supportsystem.receiver.receiverModel.EngineParameter;

@Component
@AllArgsConstructor
public class EngineParameterMapper {
    private final ModelMapper modelMapper;

//    public ReceiverEngineParameterDto convertToDto(EngineParameter engineParameter) {
//        return modelMapper.map(engineParameter, ReceiverEngineParameterDto.class);
//    }
//
//    public EngineParameter convertToEntity(ReceiverEngineParameterDto engineParameterDto) {
//        return modelMapper.map(engineParameterDto, EngineParameter.class);
//    }

    public EngineParameter convertBolidParameterMessageToEntity(EngineParameterDto bolidParameterMessage) {
        return modelMapper.map(bolidParameterMessage, EngineParameter.class);
    }

    public ReceiverEngineParameterDto convertBolidParameterMessageToDto(EngineParameterDto bolidParameterMessage) {
        return modelMapper.map(bolidParameterMessage, ReceiverEngineParameterDto.class);
    }


}
