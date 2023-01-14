package tr.com.huseyinaydin.casgem.bootcamp.api.controllers.users;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users.InstructorService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Paths;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.instructor.CreateInstructorRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.ChangeUserPasswordRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.instructor.UpdateInstructorRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.instructor.CreateInstructorResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.instructors.GetAllInstructorsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.instructors.GetInstructorResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.instructor.UpdateInstructorResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot
 *
 * 
 */

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(Paths.apiV1 + "instructors")
public class InstructorsController {
    private InstructorService service;

    @GetMapping
    DataResult<List<GetAllInstructorsResponse>> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    DataResult<CreateInstructorResponse> add(@Valid @RequestBody CreateInstructorRequest request) {
        return service.add(request);
    }

    @GetMapping("/{id}")
    DataResult<GetInstructorResponse> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    DataResult<UpdateInstructorResponse> update(
            @Valid @RequestBody UpdateInstructorRequest request,
            @PathVariable int id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    Result delete(@PathVariable int id) {
        return service.delete(id);
    }

    @PutMapping("/change-password/{id}")
    Result changePassword(@RequestBody ChangeUserPasswordRequest request, @PathVariable int id) {
        return service.changePassword(request, id);
    }
}
