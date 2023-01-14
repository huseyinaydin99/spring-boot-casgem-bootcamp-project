package tr.com.huseyinaydin.casgem.bootcamp.api.controllers.blacklists;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.blacklists.BlacklistService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Paths;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.blacklist.CreateBlacklistRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.blacklist.UpdateBlacklistRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.blacklist.CreateBlacklistResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.blacklists.GetAllBlacklistsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.blacklists.GetBlacklistResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.blacklist.UpdateBlacklistResponse;
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
@RequestMapping(Paths.apiV1 + "blacklists")
public class BlacklistsController {
    private BlacklistService service;

    @GetMapping
    DataResult<List<GetAllBlacklistsResponse>> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    DataResult<CreateBlacklistResponse> add(@Valid @RequestBody CreateBlacklistRequest request) {
        return service.add(request);
    }

    @GetMapping("/{id}")
    DataResult<GetBlacklistResponse> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    DataResult<UpdateBlacklistResponse> update(
            @Valid @RequestBody UpdateBlacklistRequest request,
            @PathVariable int id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    Result delete(@PathVariable int id) {
        return service.delete(id);
    }
}
