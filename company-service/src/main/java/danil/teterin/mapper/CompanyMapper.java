package danil.teterin.mapper;


import danil.teterin.model.Company;
import org.danil.teterin.company.CompanyDepartmentDto;
import org.danil.teterin.company.CompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    Company toEntity(CompanyDto companyDto);

    @Mapping(target = "id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    CompanyDto toDto(Company company);

    @Mapping(target = "departments", ignore = true)
    CompanyDepartmentDto toDtoDepartment(Company company);
    @Mapping(target = "departments", ignore = true)
    Company toEntity(CompanyDepartmentDto company);

}
