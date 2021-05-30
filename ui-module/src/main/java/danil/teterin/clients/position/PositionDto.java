package danil.teterin.clients.position;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {
    private String id;
    private String name;
}