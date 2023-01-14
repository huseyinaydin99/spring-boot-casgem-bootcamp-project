package tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users;

import java.util.List;

import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.instructor.CreateInstructorRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.ChangeUserPasswordRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.instructor.UpdateInstructorRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.instructor.CreateInstructorResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.instructors.GetAllInstructorsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.instructors.GetInstructorResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.instructor.UpdateInstructorResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;

public interface InstructorService {
    DataResult<List<GetAllInstructorsResponse>> getAll();
    DataResult<GetInstructorResponse> getById(int id);
    DataResult<CreateInstructorResponse> add(CreateInstructorRequest request);
    DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest request, int id);
    Result delete(int id);
    Result changePassword(ChangeUserPasswordRequest request, int id);
    void checkIfInstructorExistById(int id);
}
