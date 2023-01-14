package tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.ValidationMessages;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationRequest {
    @Min(value = 1, message = ValidationMessages.Application.BootcampIdBlank)
    private int bootcampId;
    @Min(value = 1, message = ValidationMessages.Application.ApplicantIdBlank)
    private int applicantId;
}


