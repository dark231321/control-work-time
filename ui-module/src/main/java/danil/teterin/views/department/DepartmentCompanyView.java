package danil.teterin.views.department;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import danil.teterin.clients.company.CompanyDto;
import danil.teterin.clients.company.FeignClientCompany;
import danil.teterin.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainView.class)
/*@PWA(name = "Vaadin Control", shortName = "Control")
@Push*/
@SuppressWarnings("OptionalGetWithoutIsPresent")
@PageTitle("Cities: Skylines")
public class DepartmentCompanyView extends VerticalLayout {

    private final FeignClientCompany feignClientCompany;
    private final Grid<CompanyDto> companyDtoGrid = new Grid<>(CompanyDto.class);

    private final Button backButton             = new Button("BACK");
    private final Button addButton              = new Button("ADD");
    private final Button editButton             = new Button("EDIT");
    private final Button deleteButton           = new Button("DELETE");

    @Autowired
    public DepartmentCompanyView(FeignClientCompany feignClientCompany){
        this.feignClientCompany = feignClientCompany;
        HorizontalLayout back = new HorizontalLayout();

        add(back);
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
//        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
//        editButton.setIcon(VaadinIcon.PENCIL);
//        deleteButton.setIcon(VaadinIcons.MINUS);
//        addButton.setIcon(VaadinIcons.PLUS);
        companyDtoGrid.setSizeFull();
        companyDtoGrid.setColumns("id", "address", "country");
//        feignClientCompany.findAll().subscribe(companyDtoGr   id::setItems);
        add(companyDtoGrid);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(addButton, editButton, deleteButton);
        horizontalLayout.setSizeFull();
        horizontalLayout.setVerticalComponentAlignment(Alignment.CENTER, editButton);
        horizontalLayout.setVerticalComponentAlignment(Alignment.END, editButton);
        horizontalLayout.setVerticalComponentAlignment(Alignment.START, deleteButton);
        add(horizontalLayout);
    }
}
