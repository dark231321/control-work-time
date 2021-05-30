package org.danil.teterin.company;

import lombok.*;
import org.danil.teterin.department.DepartmentDto;

import java.util.List;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDepartmentDto {
    private Integer id;
    private String address;
    private String country;
    private List<DepartmentDto> departments;
}
