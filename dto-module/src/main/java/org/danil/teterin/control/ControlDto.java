package org.danil.teterin.control;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ControlDto {
    private String id;
    private Date out;
    private Date enter;
    private String doorFk;
    private String employeeFk;
}
