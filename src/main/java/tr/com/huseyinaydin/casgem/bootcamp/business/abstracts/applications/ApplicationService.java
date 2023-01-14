package tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.applications;

import java.util.List;

import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.application.CreateApplicationRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.application.UpdateApplicationRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.application.CreateApplicationResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.applications.GetAllApplicationsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.applications.GetApplicationResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.application.UpdateApplicationResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;

public interface ApplicationService {
    DataResult<List<GetAllApplicationsResponse>> getAll();
    DataResult<GetApplicationResponse> getById(int id);
    DataResult<CreateApplicationResponse> add(CreateApplicationRequest request);
    DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest request, int id);
    Result delete(int id);
    Result findApplicationAndDeleteFromApplication(int applicantId);
}
