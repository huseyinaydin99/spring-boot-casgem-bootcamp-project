package tr.com.huseyinaydin.casgem.bootcamp.api.controllers.applications;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.applications.ApplicationService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Paths;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.application.CreateApplicationRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.application.UpdateApplicationRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.application.CreateApplicationResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.applications.GetAllApplicationsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.applications.GetApplicationResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.application.UpdateApplicationResponse;
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
@RequestMapping(Paths.apiV1 + "applications")
public class ApplicationsController {
    private ApplicationService service;

    @GetMapping
    DataResult<List<GetAllApplicationsResponse>> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    DataResult<CreateApplicationResponse> add(@Valid @RequestBody CreateApplicationRequest request) {
        return service.add(request);
    }

    @GetMapping("/{id}")
    DataResult<GetApplicationResponse> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    DataResult<UpdateApplicationResponse> update(
            @Valid @RequestBody UpdateApplicationRequest request,
            @PathVariable int id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    Result delete(@PathVariable int id) {
        return service.delete(id);
    }
}

