package danil.teterin.views.company;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
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
import reactor.core.publisher.Mono;

import java.util.List;

@Route(value = "Company", layout = MainView.class)
/*@PWA(name = "Vaadin Control", shortName = "Control")
@Push*/
@SuppressWarnings("OptionalGetWithoutIsPresent")
@PageTitle("Company: grid")
public class CompanyView extends VerticalLayout {

    private final FeignClientCompany feignClientCompany;
    private Grid<CompanyDto> companyDtoGrid;

    private final Button backButton             = new Button("BACK");
    private final Button addButton              = new Button("ADD");
    private final Button editButton             = new Button("EDIT");
    private final Button deleteButton           = new Button("DELETE");

    @Autowired
    public CompanyView(FeignClientCompany feignClientCompany){
        this.feignClientCompany = feignClientCompany;

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (attachEvent.isInitialAttach()) {
            companyDtoGrid = new Grid<>(CompanyDto.class);
            HorizontalLayout back = new HorizontalLayout();
            back.add(backButton);
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
            companyDtoGrid.setColumns("address", "country");
            setGridValuesReactive();
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.add(addButton, editButton, deleteButton);
            horizontalLayout.setSizeFull();
            horizontalLayout.setVerticalComponentAlignment(Alignment.CENTER, editButton);
            horizontalLayout.setVerticalComponentAlignment(Alignment.END, editButton);
            horizontalLayout.setVerticalComponentAlignment(Alignment.START, deleteButton);
            addClickListener();
            add(back, companyDtoGrid, horizontalLayout);
        }
    }

    private void setGridValuesReactive() {
        UI ui = getUI().get();
        feignClientCompany.findAll().collectList().subscribe(
                department -> ui.access(() -> companyDtoGrid.setItems(department)));

    }

    private void addClickListener(){
        addButton.addClickListener(
                e -> feignClientCompany.save(companyDtoGrid.asSingleSelect().getValue())
        );

        deleteButton.addClickListener(
                e -> feignClientCompany.delete(companyDtoGrid.asSingleSelect().getValue().getId())
        );

    }

}
