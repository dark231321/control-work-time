package danil.teterin.dto.department;

import danil.teterin.dto.company.CompanyDto;
import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCompanyDto {
    private int id;
    private String name;
    private CompanyDto companyDto;
}
