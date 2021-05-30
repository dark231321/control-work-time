package danil.teterin.mapper;

import danil.teterin.model.AccessLevel;
import danil.teterin.model.Door;
import org.danil.teterin.door.DoorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoorMapper {
    Door toEntity(DoorDto dto);
    DoorDto toDto(Door door);
}
