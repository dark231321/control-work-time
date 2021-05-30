package danil.teterin.mapper;


import danil.teterin.model.AccessLevel;
import org.danil.teterin.accesslevel.AccessLevelDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessLevelMapper {
    AccessLevel toEntity(AccessLevelDto accessLevelDto);
    AccessLevelDto toDto(AccessLevel accessLevel);
}
