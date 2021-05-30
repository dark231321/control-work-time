package org.danil.teterin.company;

import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CompanyDto {

    private Integer id;
    @ToString.Include
    private String name;
    @ToString.Include
    private String address;
    @ToString.Include
    private String country;

}
