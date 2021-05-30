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
import danil.teterin.clients.door.DoorFeignClient;
import danil.teterin.views.MainView;
import danil.teterin.views.department.DepartmentEditorDialog;
import org.danil.teterin.department.DepartmentDto;
import org.danil.teterin.door.DoorDto;
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
    private final Button deleteButton           = new Button("DELETE");

    @Autowired
    public DoorView(DoorFeignClient doorFeignClient){
        this.doorFeignClient = doorFeignClient;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (attachEvent.isInitialAttach()) {
            departmentDtoGrid = new Grid<>(DoorDto.class);
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
            add(departmentDtoGrid, horizontalLayout);
        }
    }

    private void setGridValuesReactive() {
        UI ui = getUI().get();
        doorFeignClient.findAll().collectList().subscribe(
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
                    DoorEditorDialog doorEditorDialog
                            = new DoorEditorDialog(doorFeignClient,
                            departmentDtoGrid.asSingleSelect().getValue());
                    doorEditorDialog.open();
                    while (!doorEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        addButton.addClickListener(
                e -> {
                    DoorEditorDialog doorEditorDialog
                            = new DoorEditorDialog(doorFeignClient,
                            DoorDto.builder().build());
                    doorEditorDialog.open();
                    while (!doorEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        deleteButton.addClickListener(
                e -> doorFeignClient.delete(departmentDtoGrid.asSingleSelect().getValue().getId())
        );
    }
}
