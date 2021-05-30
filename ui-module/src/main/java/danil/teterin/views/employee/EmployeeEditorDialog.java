package danil.teterin.views.employee;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import danil.teterin.clients.access.AccessFeignClient;
import danil.teterin.clients.department.DepartmentFeignClient;
import danil.teterin.clients.employee.FeignEmployeeClient;
import danil.teterin.clients.position.FeignPositionClient;
import org.danil.teterin.accesslevel.AccessLevelDto;
import org.danil.teterin.department.DepartmentDto;
import org.danil.teterin.employee.EmployeeDto;
import org.danil.teterin.position.PositionDto;

import java.time.Instant;
import java.time.LocalDate;

public class EmployeeEditorDialog {
    private final TextField lastname                = new TextField("lastname: ");
    private final TextField firstname               = new TextField("firstname: ");
    private final TextField middlename              = new TextField("middlename: ");
    private final TextField passport_series         = new TextField("passport series: ");
    private final TextField passport_number         = new TextField("passport number: ");
    private final DatePicker dateOfBirthday         = new DatePicker ("Date Of Birthday: ");

    private Select<DepartmentDto>       departmentNativeSelect;
    private Select<PositionDto>         positionNativeSelect;
    private Select<AccessLevelDto>      accessLevelDtoNativeSelect;

    private final EmployeeDto employeeDto;
    private final DepartmentFeignClient departmentService;
    private final FeignPositionClient positionService;
    private final AccessFeignClient accessLevelService;
    private final FeignEmployeeClient employeeService;

    public EmployeeEditorDialog(EmployeeDto employeeDto,
                                DepartmentFeignClient departmentService,
                                FeignPositionClient positionService,
                                AccessFeignClient accessLevelService,
                                FeignEmployeeClient employeeService) {
        this.employeeDto = employeeDto;
        this.departmentService = departmentService;
        this.positionService = positionService;
        this.accessLevelService = accessLevelService;
        this.employeeService = employeeService;
        init();
    }

    private void init() {
        lastname.setValue(employeeDto.getLastname());
        firstname.setValue(employeeDto.getFirstname());
        middlename.setValue(employeeDto.getMiddlename());
        passport_series.setValue(employeeDto.getPassport_series());
        passport_number.setValue(employeeDto.getPassport_number());
        dateOfBirthday.setValue(LocalDate.from(Instant.ofEpochMilli(employeeDto.getDateOfBirthday().getTime())));

    }
}
