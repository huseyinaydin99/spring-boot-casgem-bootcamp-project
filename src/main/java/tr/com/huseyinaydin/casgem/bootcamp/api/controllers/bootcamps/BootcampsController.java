package tr.com.huseyinaydin.casgem.bootcamp.api.controllers.bootcamps;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.bootcamps.BootcampService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Paths;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.bootcamp.CreateBootcampRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.bootcamp.UpdateBootcampRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.bootcamp.CreateBootcampResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.bootcamps.GetAllBootcampsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.bootcamps.GetBootcampResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.bootcamp.UpdateBootcampResponse;
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
@RequestMapping(Paths.apiV1 + "bootcamps")
public class BootcampsController {
    private BootcampService service;

    @GetMapping
    DataResult<List<GetAllBootcampsResponse>> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    DataResult<CreateBootcampResponse> add(@Valid @RequestBody CreateBootcampRequest request) {
        return service.add(request);
    }

    @GetMapping("/{id}")
    DataResult<GetBootcampResponse> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    DataResult<UpdateBootcampResponse> update(@Valid @RequestBody UpdateBootcampRequest request, @PathVariable int id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    Result delete(@PathVariable int id) {
        return service.delete(id);
    }
}