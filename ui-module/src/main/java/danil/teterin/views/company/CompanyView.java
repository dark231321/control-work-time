package danil.teterin.views.company;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import danil.teterin.clients.company.FeignClientCompany;
import danil.teterin.views.MainView;
import org.danil.teterin.company.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Company", layout = MainView.class)
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
            companyDtoGrid.setColumns("name", "address", "country");
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
        companyDtoGrid.addSelectionListener(valueChangeEvent ->
        {
            if (!companyDtoGrid.asSingleSelect().isEmpty()) {
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            } else {
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });

        editButton.addClickListener(
                event -> {
                    CompanyEditorDialog companyEditorDialog
                            = new CompanyEditorDialog(feignClientCompany, companyDtoGrid.asSingleSelect().getValue());
                    companyEditorDialog.open();
                    while (!companyEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        addButton.addClickListener(
             e -> {
                 CompanyEditorDialog companyEditorDialog
                         = new CompanyEditorDialog(feignClientCompany, null);
                 companyEditorDialog.open();
                 while (!companyEditorDialog.isOpened())
                     this.setGridValuesReactive();
             }
        );

        deleteButton.addClickListener(
                e -> feignClientCompany.delete(companyDtoGrid.asSingleSelect().getValue().getId())
        );

    }

}
