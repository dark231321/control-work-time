package danil.teterin.views.position;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import danil.teterin.clients.access.AccessFeignClient;
import danil.teterin.clients.position.FeignPositionClient;
import org.danil.teterin.accesslevel.AccessLevelDto;
import org.danil.teterin.position.PositionDto;

public class PositionEditorDialog extends Dialog {
    private final TextField nameOfCompany              = new TextField("Name: ");

    private final Button save;
    private final Button cancel;
    private final Button delete;

    private final FeignPositionClient feignPositionClient;
    private PositionDto positionDto;

    public PositionEditorDialog(FeignPositionClient feignPositionClient,
                                PositionDto position) {
        this.feignPositionClient = feignPositionClient;
        this.positionDto = position;
        init();
        save   = new Button("Save",   event -> {
            positionDto.setName(nameOfCompany.getValue());
            feignPositionClient.save(positionDto).subscribe();
            this.close();
        });
        cancel = new Button("Cancel", event -> this.close());
        delete = new Button("Delete", event -> {
            feignPositionClient.delete(positionDto.getId()).subscribe();
            this.close();
        });
        add(new VerticalLayout(nameOfCompany,
                new HorizontalLayout(cancel, save, delete)));
    }

    private void init() {
        if (positionDto!=null)
            nameOfCompany.setValue(positionDto.getName());
        else
            positionDto = PositionDto.builder().build();
    }
}
