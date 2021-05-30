package danil.teterin.views.door;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import danil.teterin.clients.door.DoorDto;
import danil.teterin.clients.door.DoorFeignClient;
import danil.teterin.clients.employee.EmployeeDto;
import danil.teterin.clients.employee.FeignEmployeeClient;
import danil.teterin.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Door", layout = MainView.class)
/*@PWA(name = "Vaadin Control", shortName = "Control")
@Push*/
@SuppressWarnings("OptionalGetWithoutIsPresent")
@PageTitle("Door: grid")
public class DoorView extends VerticalLayout {
    private final DoorFeignClient doorFeignClient;
    private Grid<DoorDto> departmentDtoGrid;

    private final Button backButton             = new Button("BACK");
    private final Button addButton              = new Button("ADD");
    private final Button editButton             = new Button("EDIT");
    private Button deleteButton ;

    @Autowired
    public DoorView(DoorFeignClient doorFeignClient){
        this.doorFeignClient = doorFeignClient;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (attachEvent.isInitialAttach()) {
            departmentDtoGrid = new Grid<>(DoorDto.class);
            HorizontalLayout back = new HorizontalLayout();
            back.add(backButton);
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
            departmentDtoGrid.setColumns("name");
            setGridValuesReactive();
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.add(addButton, editButton, deleteButton);
            horizontalLayout.setSizeFull();
            horizontalLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, editButton);
            horizontalLayout.setVerticalComponentAlignment(FlexComponent.Alignment.END, editButton);
            horizontalLayout.setVerticalComponentAlignment(FlexComponent.Alignment.START, deleteButton);
            addButtonListner();
            add(back, departmentDtoGrid, horizontalLayout);
        }
    }

    private void setGridValuesReactive() {
        UI ui = getUI().get();
        doorFeignClient.findAll().collectList().subscribe(
                department -> ui.access(() -> departmentDtoGrid.setItems(department)));

    }

    private void addButtonListner(){
        deleteButton    = new Button("DELETE",
                e ->  doorFeignClient.delete(departmentDtoGrid.asSingleSelect().getValue().getId()));

    }
}
