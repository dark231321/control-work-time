package danil.teterin.views.department;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import danil.teterin.clients.company.FeignClientCompany;
import danil.teterin.clients.department.DepartmentFeignClient;
import danil.teterin.views.MainView;
import danil.teterin.views.company.CompanyEditorDialog;
import org.danil.teterin.company.CompanyDto;
import org.danil.teterin.department.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Department", layout = MainView.class)
/*@PWA(name = "Vaadin Control", shortName = "Control")
@Push*/
@SuppressWarnings("OptionalGetWithoutIsPresent")
@PageTitle("Department: grid")
public class DepartmentView extends VerticalLayout {
    private final FeignClientCompany feignClientCompany;
    private final DepartmentFeignClient departmentFeignClient;
    private Grid<DepartmentDto> departmentDtoGrid;

    private final Button backButton             = new Button("BACK");
    private final Button addButton              = new Button("ADD");
    private final Button editButton             = new Button("EDIT");
    private final Button deleteButton           = new Button("DELETE");

    @Autowired
    public DepartmentView(FeignClientCompany feignClientCompany,
                          DepartmentFeignClient departmentFeignClient){
        this.feignClientCompany = feignClientCompany;
        this.departmentFeignClient = departmentFeignClient;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (attachEvent.isInitialAttach()) {
            departmentDtoGrid = new Grid<>(DepartmentDto.class);
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
            departmentDtoGrid.setColumns("name");
            setGridValuesReactive();
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.add(addButton, editButton, deleteButton);
            horizontalLayout.setSizeFull();
            horizontalLayout.setVerticalComponentAlignment(Alignment.CENTER, editButton);
            horizontalLayout.setVerticalComponentAlignment(Alignment.END, editButton);
            horizontalLayout.setVerticalComponentAlignment(Alignment.START, deleteButton);
            addButtonListner();
            add(departmentDtoGrid, horizontalLayout);
        }
    }

    private void setGridValuesReactive() {
        UI ui = getUI().get();
        departmentFeignClient.findAll().collectList().subscribe(
                department -> ui.access(() -> departmentDtoGrid.setItems(department)));

    }

    private void addButtonListner(){
        departmentDtoGrid.addSelectionListener(valueChangeEvent ->
        {
            if (!departmentDtoGrid.asSingleSelect().isEmpty()) {
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            } else {
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });

        editButton.addClickListener(
                event -> {
                    DepartmentEditorDialog departmentEditorDialog
                            = new DepartmentEditorDialog(feignClientCompany,
                            departmentFeignClient,
                            departmentDtoGrid.asSingleSelect().getValue());
                    departmentEditorDialog.open();
                    while (!departmentEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        addButton.addClickListener(
                e -> {
                    DepartmentEditorDialog departmentEditorDialog
                            = new DepartmentEditorDialog(feignClientCompany,
                            departmentFeignClient,
                            DepartmentDto.builder().build());
                    departmentEditorDialog.open();
                    while (!departmentEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        deleteButton.addClickListener(
                e -> feignClientCompany.delete(departmentDtoGrid.asSingleSelect().getValue().getId())
        );
    }


}
