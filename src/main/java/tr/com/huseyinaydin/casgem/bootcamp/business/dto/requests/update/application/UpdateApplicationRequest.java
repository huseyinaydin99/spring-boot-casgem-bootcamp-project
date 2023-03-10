package tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.ValidationMessages;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApplicationRequest {
    @Min(value = 1, message = ValidationMessages.Application.BootcampIdBlank)
    private int bootcampId;
    @Min(value = 1, message = ValidationMessages.Application.ApplicantIdBlank)
    private int applicantId;
    @Min(value = 1, message = ValidationMessages.Application.MinState)
    @Max(value = 4, message = ValidationMessages.Application.MaxState)
    private int stateId;
}
