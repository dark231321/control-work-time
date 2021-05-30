package danil.teterin.views.door;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import danil.teterin.clients.department.DepartmentFeignClient;
import danil.teterin.clients.door.DoorFeignClient;
import org.danil.teterin.department.DepartmentDto;
import org.danil.teterin.door.DoorDto;

public class DoorEditorDialog extends Dialog {

    private final TextField nameOfCompany              = new TextField("Name of door: ");

    private Button save;
    private Button cancel;
    private Button delete;

    private DoorFeignClient doorFeignClient;
    private DoorDto doorDto;

    public DoorEditorDialog(DoorFeignClient doorFeignClient,
                            DoorDto doorDto) {
        this.doorFeignClient = doorFeignClient;
        this.doorDto = doorDto;init();

        save   = new Button("Save", event -> {
            doorDto.setName(nameOfCompany.getValue());
            doorFeignClient.save(doorDto);
        });
        cancel = new Button("Cancel", event -> this.close());
        delete = new Button("Delete", event -> doorFeignClient.delete(doorDto.getId()));
        add(new VerticalLayout(nameOfCompany,
                new HorizontalLayout(cancel, save, delete)));
    }

    private void init() {
        if(doorDto != null)
            nameOfCompany.setValue(doorDto.getName());
        else
            doorDto = DoorDto.builder().build();
    }
}
