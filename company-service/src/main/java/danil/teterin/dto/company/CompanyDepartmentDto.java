package danil.teterin.dto.company;

import danil.teterin.dto.department.DepartmentDto;
import lombok.*;
import danil.teterin.model.Department;

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
