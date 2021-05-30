package org.danil.teterin.position;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {
    private Integer id;
    private String name;
}