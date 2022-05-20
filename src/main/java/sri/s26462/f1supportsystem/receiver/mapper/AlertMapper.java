package sri.s26462.f1supportsystem.receiver.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sri.s26462.f1supportsystem.model.AlertMessageDto;
import sri.s26462.f1supportsystem.receiver.receiverDto.AlertDto;

@Component
@AllArgsConstructor
public class AlertMapper {
    private final ModelMapper modelMapper;

    public AlertDto convertAlertToDto(AlertMessageDto alertMessageDto) {
        return modelMapper.map(alertMessageDto, AlertDto.class);
    }
}
