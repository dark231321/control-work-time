package danil.teterin.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends StandardEntity{

    @Column("lastname")
    private String lastname;

    @Column("firstname")
    private String firstname;

    @Column("middlename")
    private String middlename;

    @Column("passportSeries")
    private String passportSeries;

    @Column("passportNumber")
    private String passportNumber;

    @Column("dateOfBirthday")
    private Date dateOfBirthday;

    @Column("accessLevelFk")
    private String accessLevelFk;

    @Column("positionFk")
    private String positionFk;

    @Column("departmentFk")
    private String departmentFk;
}
