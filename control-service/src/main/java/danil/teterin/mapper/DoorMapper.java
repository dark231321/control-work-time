package danil.teterin.mapper;

import danil.teterin.dto.AccessLevelDto;
import danil.teterin.dto.DoorDto;
import danil.teterin.model.AccessLevel;
import danil.teterin.model.Door;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoorMapper {
    Door toEntity(DoorDto dto);
    DoorDto toDto(Door door);
}
