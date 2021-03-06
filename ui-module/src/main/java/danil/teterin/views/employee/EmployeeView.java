package danil.teterin.views.employee;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import danil.teterin.clients.access.AccessFeignClient;
import danil.teterin.clients.department.DepartmentFeignClient;
import danil.teterin.clients.employee.FeignEmployeeClient;
import danil.teterin.views.MainView;
import org.danil.teterin.employee.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Employee", layout = MainView.class)
/*@PWA(name = "Vaadin Control", shortName = "Control")
@Push*/
@SuppressWarnings("OptionalGetWithoutIsPresent")
@PageTitle("Access-Level: grid")
public class EmployeeView extends VerticalLayout {

    private final FeignEmployeeClient employeeClient;
    private final DepartmentFeignClient departmentFeignClient;
    private final AccessFeignClient accessFeignClient;

    private Grid<EmployeeDto> departmentDtoGrid;

    private final Button backButton             = new Button("BACK");
    private final Button addButton              = new Button("ADD");
    private final Button editButton             = new Button("EDIT");
    private final Button deleteButton           = new Button("DELETE");

    @Autowired
    public EmployeeView(FeignEmployeeClient employeeClient, DepartmentFeignClient departmentFeignClient, AccessFeignClient accessFeignClient){
        this.employeeClient = employeeClient;
        this.departmentFeignClient = departmentFeignClient;
        this.accessFeignClient = accessFeignClient;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (attachEvent.isInitialAttach()) {
            departmentDtoGrid = new Grid<>(EmployeeDto.class);
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
            departmentDtoGrid.setColumns("firstname", "lastname", "middlename", "passportSeries", "passportNumber", "dateOfBirthday");
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
        employeeClient.findAll().collectList().subscribe(
                department -> ui.access(() -> departmentDtoGrid.setItems(department)));

    }

    private void addButtonListner(){
        deleteButton.addClickListener(
                e -> employeeClient.delete(
                        departmentDtoGrid.asSingleSelect().getValue().getId()).subscribe()
        );

    }

}
