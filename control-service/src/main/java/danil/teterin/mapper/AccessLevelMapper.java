package danil.teterin.mapper;

import danil.teterin.dto.AccessLevelDto;
import danil.teterin.model.AccessLevel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessLevelMapper {
    AccessLevel toEntity(AccessLevelDto accessLevelDto);
    AccessLevelDto toDto(AccessLevel accessLevel);
}
