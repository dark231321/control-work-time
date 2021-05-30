package danil.teterin.dto;

import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

public class ControlDto {

    private Date out;
    private Date enter;
    private String doorFk;
    private String employeeFk;
}
