package tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApplicationResponse {
    private int id;
    private int bootcampId;
    private int applicantId;
    private int stateId;
}
