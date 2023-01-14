package tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.instructor;

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
public class UpdateInstructorRequest extends UpdateUserRequest {
    @NotBlank(message = ValidationMessages.Instructor.CompanyBlank)
    @Length(min = 2, max = 50, message = ValidationMessages.Instructor.CompanyValid)
    private String companyName;
}
