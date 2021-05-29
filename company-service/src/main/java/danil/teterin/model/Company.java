package danil.teterin.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "company")
public class Company extends BaseEntity {

    @Column("name_of_company")
    private String name;

    @Column("address")
    private String address;

    @Column("country")
    private String country;

    @Transient
    private List<Department> departments;


}
