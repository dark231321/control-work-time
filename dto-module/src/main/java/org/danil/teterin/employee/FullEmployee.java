package org.danil.teterin.employee;

import lombok.*;
import org.danil.teterin.accesslevel.AccessLevelDto;
import org.danil.teterin.department.DepartmentDto;
import org.danil.teterin.position.PositionDto;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FullEmployee {
    private String id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String passport_series;
    private String passport_number;
    private Date dateOfBirthday;
    private String role;
    private PositionDto positionDto;
    private DepartmentDto departmentDto;
    private AccessLevelDto accessLevelDto;
}
