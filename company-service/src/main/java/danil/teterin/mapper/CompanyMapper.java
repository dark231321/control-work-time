package danil.teterin.mapper;


import danil.teterin.dto.company.CompanyDepartmentDto;
import danil.teterin.dto.company.CompanyDto;
import danil.teterin.model.Company;
import org.danil.teterin.company.CompanyDepartmentDto;
import org.danil.teterin.company.CompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toEntity(CompanyDto companyDto);
    CompanyDto toDto(Company company);

    @Mapping(target = "departments", ignore = true)
    CompanyDepartmentDto toDtoDepartment(Company company);
    Company toEntity(CompanyDepartmentDto company);

}
