package tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.ValidationMessages;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.CreateUserRequest;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicantRequest extends CreateUserRequest {
    @NotBlank(message = ValidationMessages.Applicant.AboutBlank)
    @Length(min = 5, max = 50, message = ValidationMessages.Applicant.AboutValid)
    private String about;
}