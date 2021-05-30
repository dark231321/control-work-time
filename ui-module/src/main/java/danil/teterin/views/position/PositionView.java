package danil.teterin.views.position;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import danil.teterin.clients.employee.EmployeeDto;
import danil.teterin.clients.employee.FeignEmployeeClient;
import danil.teterin.clients.position.FeignPositionClient;
import danil.teterin.clients.position.PositionDto;
import danil.teterin.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Position", layout = MainView.class)
/*@PWA(name = "Vaadin Control", shortName = "Control")
@Push*/
@SuppressWarnings("OptionalGetWithoutIsPresent")
@PageTitle("Access-Level: grid")
public class PositionView extends VerticalLayout {
    private final FeignPositionClient feignPositionClient;
    private Grid<PositionDto> departmentDtoGrid;

    private final Button backButton             = new Button("BACK");
    private final Button addButton              = new Button("ADD");
    private final Button editButton             = new Button("EDIT");
    private Button deleteButton ;

    @Autowired
    public PositionView(FeignPositionClient feignPositionClient){
        this.feignPositionClient = feignPositionClient;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (attachEvent.isInitialAttach()) {
            departmentDtoGrid = new Grid<>(PositionDto.class);
            HorizontalLayout back = new HorizontalLayout();
            back.add(backButton);
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
            add(back, departmentDtoGrid, horizontalLayout);
        }
    }

    private void setGridValuesReactive() {
        UI ui = getUI().get();
        feignPositionClient.findAll().collectList().subscribe(
                department -> ui.access(() -> departmentDtoGrid.setItems(department)));

    }

    private void addButtonListner(){
        deleteButton    = new Button("DELETE",
                e ->  feignPositionClient.delete(departmentDtoGrid.asSingleSelect().getValue().getId()));

    }
}
