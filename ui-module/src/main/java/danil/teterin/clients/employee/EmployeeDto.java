package danil.teterin.clients.employee;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String passport_series;
    private String passport_number;
    private Date dateOfBirthday;
}
