package danil.teterin.mapper;

import danil.teterin.dto.department.DepartmentCompanyDto;
import danil.teterin.dto.department.DepartmentDto;
import danil.teterin.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);

    DepartmentCompanyDto toDtoComp(Department department);
    Department toEntity(DepartmentCompanyDto dto);

}
