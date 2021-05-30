package danil.teterin.clients.company;

import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private int id;
    private String address;
    private String country;

}
