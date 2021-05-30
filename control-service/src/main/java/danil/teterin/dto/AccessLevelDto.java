package danil.teterin.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccessLevelDto {
    private String id;
    private String name;
}
