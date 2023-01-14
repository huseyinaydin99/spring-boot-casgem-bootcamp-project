package tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.instructors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.GetAllUsersResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInstructorsResponse extends GetAllUsersResponse {
    private String companyName;
}
