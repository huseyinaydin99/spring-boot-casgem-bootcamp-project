package tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users;

import java.util.List;

import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.employee.CreateEmployeeRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.ChangeUserPasswordRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.employee.UpdateEmployeeRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.employee.CreateEmployeeResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.employees.GetAllEmployeesResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.employees.GetEmployeeResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.employee.UpdateEmployeeResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;

public interface EmployeeService {
    DataResult<List<GetAllEmployeesResponse>> getAll();
    DataResult<GetEmployeeResponse> getById(int id);
    DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request);
    DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest request, int id);
    Result delete(int id);
    Result changePassword(ChangeUserPasswordRequest request, int id);
    void checkIfUserIsEmployee(int id);
}
