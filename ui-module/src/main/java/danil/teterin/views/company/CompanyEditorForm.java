package danil.teterin.views.company;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import danil.teterin.clients.company.FeignClientCompany;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;

import java.awt.*;

@SpringComponent(value = "company-editor")
@UIScope
public class CompanyEditorForm extends Window {

    private final TextField nameOfCompany              = new TextField("Name: ");
    private final TextField addressOfCompany           = new TextField("Address: ");
    private final TextField countryOfCompany           = new TextField("Country: ");

    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");

    private final FeignClientCompany feignClientCompany;
    private CompanyDto companyDto;

    @Autowired
    public CompanyEditorForm(Frame owner, FeignClientCompany feignClientCompany) {
        super(owner);
        this.feignClientCompany = feignClientCompany;
        bind();
    }


    public void set(CompanyDto companyDto){
        this.companyDto = companyDto;
        companyDto.setAddress(companyDto.getName());
        companyDto.setCountry(companyDto.getCountry());
        companyDto.setAddress(companyDto.getAddress());
    }


    public Component create() {
        return new VerticalLayout(
                nameOfCompany,
                addressOfCompany,
                countryOfCompany,
                new HorizontalLayout(cancel, save)
        );
    }

    public void bind(){
        save.addClickListener(e -> feignClientCompany.save(companyDto));
        cancel.addClickListener(e -> remove(CompanyEditorForm.this));
    }
}
