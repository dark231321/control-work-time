package danil.teterin.views.department;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import danil.teterin.clients.access.AccessFeignClient;
import danil.teterin.clients.company.FeignClientCompany;
import danil.teterin.clients.department.DepartmentFeignClient;
import org.danil.teterin.accesslevel.AccessLevelDto;
import org.danil.teterin.company.CompanyDto;
import org.danil.teterin.department.DepartmentDto;

public class DepartmentEditorDialog extends Dialog {

    private final TextField nameOfCompany              = new TextField("Name of department: ");

    private Button save;
    private Button cancel;
    private Button delete;

    private FeignClientCompany feignClientCompany;
    private DepartmentFeignClient departmentFeignClient;
    private Select<CompanyDto> companyDtoSelect;
    private DepartmentDto departmentDto;

    public DepartmentEditorDialog(FeignClientCompany feignClientCompany,
                                  DepartmentFeignClient departmentFeignClient,
                                  DepartmentDto departmentDto) {
        this.departmentFeignClient = departmentFeignClient;
        this.feignClientCompany = feignClientCompany;
        this.departmentDto = departmentDto;
        init();

        save   = new Button("Save", event -> {
            departmentDto.setName(nameOfCompany.getValue());
            departmentFeignClient.save(departmentDto);
        });
        cancel = new Button("Cancel", event -> this.close());
        delete = new Button("Delete", event -> departmentFeignClient.delete(departmentDto.getId()));
        add(new VerticalLayout(nameOfCompany,
                new HorizontalLayout(cancel, save, delete)));
    }

    private void init() {
        nameOfCompany.setValue(departmentDto.getName());
        feignClientCompany.findAll().collectList().subscribe(companyDtoSelect::setItems);
    }

}
