package danil.teterin.mapper;

import danil.teterin.model.Control;
import org.danil.teterin.control.ControlDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ControlMapper {
    Control toEntity(ControlDto controlDto);
    ControlDto toDto(Control control);
}
