package danil.teterin.dto.company;


import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String id;
    private String address;
    private String country;

}
