package danil.teterin.views.company;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import danil.teterin.clients.company.CompanyDto;
import danil.teterin.clients.company.FeignClientCompany;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import java.awt.*;

@SpringComponent
@UIScope
public class CompanyEditorForm extends VerticalLayout implements KeyNotifier {

    private final TextField nameOfCompany              = new TextField("Name: ");
    private final TextField addressOfCompany           = new TextField("Address: ");
    private final TextField countryOfCompany           = new TextField("Country: ");

    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");

    private final FeignClientCompany feignClientCompany;

    @Autowired
    public CompanyEditorForm(FeignClientCompany feignClientCompany) {
        this.feignClientCompany = feignClientCompany;
    }

}
