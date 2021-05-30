package danil.teterin.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class StandardEntity {
    @Column("id")
    private String id;
}
