package tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users;

import java.util.List;

import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.applicant.CreateApplicantRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.ChangeUserPasswordRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.applicant.UpdateApplicantRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.applicant.CreateApplicantResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.applicants.GetAllApplicantsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.applicants.GetApplicantResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.applicant.UpdateApplicantResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;

public interface ApplicantService {
    DataResult<List<GetAllApplicantsResponse>> getAll();
    DataResult<GetApplicantResponse> getById(int id);
    DataResult<CreateApplicantResponse> add(CreateApplicantRequest request);
    DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest request, int id);
    Result delete(int id);
    DataResult<GetApplicantResponse> beAnApplicant(String about, int id);
    Result removeAnApplicant(int id);
    Result changePassword(ChangeUserPasswordRequest request, int id);
    void checkIfApplicantExistById(int id);
}
