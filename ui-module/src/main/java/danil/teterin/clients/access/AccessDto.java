package danil.teterin.clients.access;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccessDto {
    private String id;
    private String name;
}
