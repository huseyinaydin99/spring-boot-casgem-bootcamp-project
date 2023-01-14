package tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.applicants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.GetUserResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetApplicantResponse extends GetUserResponse {
    private String about;
}
