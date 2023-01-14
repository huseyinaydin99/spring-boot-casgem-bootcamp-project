package tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.instructor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.CreateUserResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInstructorResponse extends CreateUserResponse {
    private String companyName;
}
