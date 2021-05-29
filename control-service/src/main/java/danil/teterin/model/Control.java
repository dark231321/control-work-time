package danil.teterin.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "control")
public class Control extends BaseEntity {

    @Column(value = "out")
    private Date out;

    @Column(value = "enter")
    private Date enter;

    @Column(value = "door_fk")
    private String doorFk;

    @Column(value = "employee_fk")
    private String employeeFk;
}
