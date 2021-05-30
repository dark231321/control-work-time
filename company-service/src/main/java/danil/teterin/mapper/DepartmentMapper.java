package danil.teterin.mapper;

import danil.teterin.model.Department;
import org.danil.teterin.department.DepartmentDto;
import org.danil.teterin.department.DepartmentWithCompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);

    @Mapping(target = "companyDto", ignore = true)
    DepartmentWithCompanyDto toDtoComp(Department department);
    @Mapping(target = "companyId", ignore = true)
    Department toEntity(DepartmentWithCompanyDto dto);

}
