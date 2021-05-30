package danil.teterin.views.company;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import danil.teterin.clients.company.FeignClientCompany;
import org.danil.teterin.company.CompanyDto;

public class CompanyEditorDialog extends Dialog {

    private final TextField nameOfCompany              = new TextField("Name: ");
    private final TextField addressOfCompany           = new TextField("Address: ");
    private final TextField countryOfCompany           = new TextField("Country: ");

    private Button save;
    private Button cancel;
    private Button delete;

    private FeignClientCompany feignClientCompany;
    private CompanyDto companyDto;

    public CompanyEditorDialog(FeignClientCompany feignClientCompany,
                               CompanyDto company) {
        this.feignClientCompany = feignClientCompany;
        this.companyDto = company;
        init();
        System.out.println(companyDto);
        save   = new Button("Save",   event -> {
            System.out.println(companyDto);
            companyDto.setName(nameOfCompany.getValue());
            companyDto.setAddress(addressOfCompany.getValue());
            companyDto.setCountry(countryOfCompany.getValue());
            feignClientCompany.save(companyDto).subscribe();
            this.close();
        });

        cancel = new Button("Cancel", event -> this.close());
        delete = new Button("Delete", event -> {
            feignClientCompany.delete(companyDto.getId());
            this.close();
        });
        add(new VerticalLayout(nameOfCompany,
                addressOfCompany,
                countryOfCompany,
                new HorizontalLayout(cancel, save, delete)));
    }

    private void init() {
        if(companyDto != null) {
            nameOfCompany.setValue(companyDto.getName());
            addressOfCompany.setValue(companyDto.getAddress());
            countryOfCompany.setValue(companyDto.getCountry());
        } else
        {
            companyDto = CompanyDto.builder().build();
        }
    }
}
