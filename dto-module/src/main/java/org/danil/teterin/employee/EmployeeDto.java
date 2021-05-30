package org.danil.teterin.employee;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Integer id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String passport_series;
    private String passport_number;
    private Date dateOfBirthday;
}
