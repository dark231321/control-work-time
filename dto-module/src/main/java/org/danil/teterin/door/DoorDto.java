package org.danil.teterin.door;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoorDto {
    private Integer id;
    private String name;
}
