package danil.teterin.model;

import danil.teterin.feign.DepartmentDto;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "door")
public class Door extends BaseEntity {

    @Column(value = "name")
    private String name;

    @Column(value = "department_fk")
    private int departmentFk;

    @Transient
    private DepartmentDto departmentDto;
}
