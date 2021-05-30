package org.danil.teterin.department;

import lombok.*;
import org.danil.teterin.company.CompanyDto;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentWithCompanyDto {
    private int id;
    private String name;
    private CompanyDto companyDto;
}
