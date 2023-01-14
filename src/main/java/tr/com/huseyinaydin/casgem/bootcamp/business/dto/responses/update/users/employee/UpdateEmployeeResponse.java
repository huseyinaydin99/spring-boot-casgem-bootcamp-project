package tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.UpdateUserResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeResponse extends UpdateUserResponse {
    private String position;
}
