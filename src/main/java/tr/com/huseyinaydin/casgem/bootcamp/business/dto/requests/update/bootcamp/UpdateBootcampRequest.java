package tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.bootcamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.ValidationMessages;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBootcampRequest {
    private int instructorId;
    @NotBlank(message = ValidationMessages.Bootcamp.NameBlank)
    @Length(min = 3, max = 50, message = ValidationMessages.Bootcamp.NameValid)
    private String name;
    @Min(value = 1, message = ValidationMessages.Bootcamp.MinState)
    @Max(value = 2, message = ValidationMessages.Bootcamp.MaxState)
    private int state;
    @NotNull(message = ValidationMessages.Bootcamp.StartDateBlank)
    private LocalDate startDate;
    @NotNull(message = ValidationMessages.Bootcamp.EndDateBlank)
    private LocalDate endDate;
}
