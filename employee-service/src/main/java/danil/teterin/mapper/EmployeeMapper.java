package danil.teterin.mapper;

import danil.teterin.dto.EmployeeDto;
import danil.teterin.dto.PositionDto;
import danil.teterin.model.Employee;
import danil.teterin.model.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeDto controlDto);
    EmployeeDto toDto(Employee control);
}
