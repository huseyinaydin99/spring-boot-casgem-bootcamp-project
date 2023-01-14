package tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.bootcamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBootcampResponse {
    private int id;
    private int instructorId;
    private String name;
    private int state;
    private LocalDate startDate;
    private LocalDate endDate;
}