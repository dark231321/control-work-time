package danil.teterin.views.access;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import danil.teterin.clients.access.AccessFeignClient;
import danil.teterin.views.MainView;
import danil.teterin.views.company.CompanyEditorDialog;
import org.danil.teterin.accesslevel.AccessLevelDto;
import org.danil.teterin.company.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Access-Level", layout = MainView.class)
/*@PWA(name = "Vaadin Control", shortName = "Control")
@Push*/
@SuppressWarnings("OptionalGetWithoutIsPresent")
@PageTitle("Access-Level: grid")
public class AccessView extends VerticalLayout {
    private final AccessFeignClient accessFeignClient;
    private Grid<AccessLevelDto> departmentDtoGrid;

    private final Button backButton             = new Button("BACK");
    private final Button addButton              = new Button("ADD");
    private final Button editButton             = new Button("EDIT");
    private Button deleteButton ;

    @Autowired
    public AccessView(AccessFeignClient accessFeignClient){
        this.accessFeignClient = accessFeignClient;
    }
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (attachEvent.isInitialAttach()) {
            departmentDtoGrid = new Grid<>(AccessLevelDto.class);
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
        accessFeignClient.findAll().collectList().subscribe(
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
                    AccessEditorDialog accessEditorDialog
                            = new AccessEditorDialog(accessFeignClient, departmentDtoGrid.asSingleSelect().getValue());
                    accessEditorDialog.open();
                    while (!accessEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        addButton.addClickListener(
                e -> {
                    AccessEditorDialog accessEditorDialog
                            = new AccessEditorDialog(accessFeignClient, null);
                    accessEditorDialog.open();
                    while (!accessEditorDialog.isOpened())
                        this.setGridValuesReactive();
                }
        );

        deleteButton.addClickListener(
                e -> accessFeignClient.delete(departmentDtoGrid.asSingleSelect().getValue().getId()).subscribe()
        );
    }
}
