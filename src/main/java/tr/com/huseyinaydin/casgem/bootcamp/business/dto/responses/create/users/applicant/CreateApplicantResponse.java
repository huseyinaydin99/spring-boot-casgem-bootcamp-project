package tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.CreateUserResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicantResponse extends CreateUserResponse {
    private String about;
}
