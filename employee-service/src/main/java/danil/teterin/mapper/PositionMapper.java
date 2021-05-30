package danil.teterin.mapper;

import danil.teterin.dto.PositionDto;
import danil.teterin.model.Position;
import org.danil.teterin.position.PositionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    Position toEntity(PositionDto controlDto);
    PositionDto toDto(Position control);
}
