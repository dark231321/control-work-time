package danil.teterin.views.company;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import danil.teterin.clients.company.FeignClientCompany;

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
                               CompanyDto companyDto) {
        this.feignClientCompany = feignClientCompany;
        this.companyDto = companyDto;

        save   = new Button("Save",   event -> {
            companyDto.setName(addressOfCompany.getValue());
            companyDto.setAddress(addressOfCompany.getValue());
            companyDto.setCountry(countryOfCompany.getValue());
            feignClientCompany.save(companyDto);
        });

        cancel = new Button("Cancel", event -> this.close());
        delete = new Button("Delete", event -> feignClientCompany.delete(companyDto.getId()));
        add(new VerticalLayout(nameOfCompany,
                addressOfCompany,
                countryOfCompany,
                new HorizontalLayout(cancel, save, delete)));
    }
}
