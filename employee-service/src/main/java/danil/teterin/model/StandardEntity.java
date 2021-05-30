package danil.teterin.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class StandardEntity {
    @Id
    @Column("id")
    private Integer id;
}
