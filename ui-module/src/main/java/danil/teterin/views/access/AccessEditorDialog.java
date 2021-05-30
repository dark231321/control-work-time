package danil.teterin.views.access;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import danil.teterin.clients.access.AccessFeignClient;
import org.danil.teterin.accesslevel.AccessLevelDto;

public class AccessEditorDialog extends Dialog {
    private final TextField nameOfCompany              = new TextField("Name: ");

    private Button save;
    private Button cancel;
    private Button delete;

    private AccessFeignClient feignClientCompany;
    private AccessLevelDto accessDto;

    public AccessEditorDialog(AccessFeignClient feignClientCompany,
                              AccessLevelDto accessDto) {
        this.feignClientCompany = feignClientCompany;
        this.accessDto = accessDto;
        init();
        save   = new Button("Save",   event -> {
            accessDto.setName(nameOfCompany.getValue());
            feignClientCompany.save(accessDto);
        });
        cancel = new Button("Cancel", event -> this.close());
        delete = new Button("Delete", event -> feignClientCompany.delete(accessDto.getId()));
        add(new VerticalLayout(nameOfCompany,
                new HorizontalLayout(cancel, save, delete)));
    }

    private void init() {
        if (accessDto != null)
            nameOfCompany.setValue(accessDto.getName());
        else
            accessDto = AccessLevelDto.builder().build();
    }


}
