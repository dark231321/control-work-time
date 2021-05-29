package danil.teterin.mapper;

import danil.teterin.dto.AccessLevelDto;
import danil.teterin.dto.ControlDto;
import danil.teterin.model.AccessLevel;
import danil.teterin.model.Control;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ControlMapper {
    Control toEntity(ControlDto controlDto);
    ControlDto toDto(Control control);
}
