package tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.bootcamps;

import java.util.List;

import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.bootcamp.CreateBootcampRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.bootcamp.UpdateBootcampRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.bootcamp.CreateBootcampResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.bootcamps.GetAllBootcampsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.bootcamps.GetBootcampResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.bootcamp.UpdateBootcampResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;

public interface BootcampService {
    DataResult<List<GetAllBootcampsResponse>> getAll();
    DataResult<GetBootcampResponse> getById(int id);
    DataResult<CreateBootcampResponse> add(CreateBootcampRequest request);
    DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest request, int id);
    Result delete(int id);
    void checkIfBootcampIsActive(int id);
    void checkIfBootcampExistById(int id);
}
