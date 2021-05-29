package danil.teterin.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "department")
public class Department extends BaseEntity {

    @Column("name_of_department")
    private String name;

    @Column("fk_company")
    private int companyId;
}
