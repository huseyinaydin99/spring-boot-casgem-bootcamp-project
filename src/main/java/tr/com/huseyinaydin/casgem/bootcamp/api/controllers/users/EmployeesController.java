package tr.com.huseyinaydin.casgem.bootcamp.api.controllers.users;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users.EmployeeService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Paths;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.employee.CreateEmployeeRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.ChangeUserPasswordRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.employee.UpdateEmployeeRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.employee.CreateEmployeeResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.employees.GetAllEmployeesResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.employees.GetEmployeeResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.employee.UpdateEmployeeResponse;
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
@RequestMapping(Paths.apiV1 + "employees")
public class EmployeesController {
    private EmployeeService service;

    @GetMapping
    DataResult<List<GetAllEmployeesResponse>> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    DataResult<CreateEmployeeResponse> add(@Valid @RequestBody CreateEmployeeRequest request) {
        return service.add(request);
    }

    @GetMapping("/{id}")
    DataResult<GetEmployeeResponse> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    DataResult<UpdateEmployeeResponse> update(@Valid @RequestBody UpdateEmployeeRequest request, @PathVariable int id) {
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
