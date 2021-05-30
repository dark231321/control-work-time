package danil.teterin.views.position;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import danil.teterin.clients.position.FeignPositionClient;
import danil.teterin.views.MainView;
import danil.teterin.views.door.DoorEditorDialog;
import org.danil.teterin.door.DoorDto;
import org.danil.teterin.position.PositionDto;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Position", layout = MainView.class)
@SuppressWarnings("OptionalGetWithoutIsPresent")
@PageTitle("Position: grid")
public class PositionView extends VerticalLayout {
    private final FeignPositionClient feignPositionClient;
    private static Grid<PositionDto> departmentDtoGrid;

    private final Button addButton              = new Button("ADD");
    private final Button editButton             = new Button("EDIT");
    private final Button deleteButton           = new Button("DELETE");

    @Autowired
    public PositionView(FeignPositionClient feignPositionClient){
        this.feignPositionClient = feignPositionClient;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (attachEvent.isInitialAttach()) {
            departmentDtoGrid = new Grid<>(PositionDto.class);
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
        feignPositionClient.findAll().collectList().subscribe(
                department -> ui.access(() -> departmentDtoGrid.setItems(department))
        );

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
                    PositionEditorDialog positionEditorDialog
                            = new PositionEditorDialog(feignPositionClient,
                            departmentDtoGrid.asSingleSelect().getValue());
                    positionEditorDialog.open();
                    while (!positionEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        addButton.addClickListener(
                e -> {
                    PositionEditorDialog positionEditorDialog
                            = new PositionEditorDialog(feignPositionClient, null);
                    positionEditorDialog.open();
                    while (!positionEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        deleteButton.addClickListener(
                e -> feignPositionClient.delete(departmentDtoGrid.asSingleSelect().getValue().getId())
        );
    }

}
