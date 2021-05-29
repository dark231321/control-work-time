package danil.teterin.dto.department;

import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private int id;
    private String name;
}
