package danil.teterin.feign.dto;

import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private String id;
    private String name;
}
