package tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.ValidationMessages;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.UpdateUserRequest;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest extends UpdateUserRequest {
    @NotBlank(message = ValidationMessages.Employee.PositionBlank)
    @Length(min = 2, max = 50, message = ValidationMessages.Employee.PositionValid)
    private String position;
}
