package tr.com.huseyinaydin.casgem.bootcamp.business.concretes.users;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users.EmployeeService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Messages;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.ValidationMessages;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.employee.CreateEmployeeRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.ChangeUserPasswordRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.employee.UpdateEmployeeRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.employee.CreateEmployeeResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.employees.GetAllEmployeesResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.employees.GetEmployeeResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.employee.UpdateEmployeeResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.exceptions.BusinessException;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.mapping.ModelMapperService;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessDataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessResult;
import tr.com.huseyinaydin.casgem.bootcamp.entities.users.Employee;
import tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.users.EmployeeRepository;

import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository repository;
    private ModelMapperService mapper;

    @Override
    public DataResult<List<GetAllEmployeesResponse>> getAll() {
        List<Employee> employees = repository.findAll();
        List<GetAllEmployeesResponse> data = employees
                .stream()
                .map(employee -> mapper.forResponse().map(employee, GetAllEmployeesResponse.class))
                .toList();

        return new SuccessDataResult<>(data, Messages.Employee.ListAll);
    }

    @Override
    public DataResult<GetEmployeeResponse> getById(int id) {
        checkIfEmployeeExistById(id);
        Employee employee = repository.findById(id).orElseThrow();
        GetEmployeeResponse data = mapper.forResponse().map(employee, GetEmployeeResponse.class);

        return new SuccessDataResult<>(data, Messages.Employee.ListById);
    }

    @Override
    public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest request) {
        comparePassword(request.getPassword(), request.getConfirmPassword());
        checkIfEmployeeExistByNationalIdentity(request.getNationalIdentity());
        Employee employee = mapper.forRequest().map(request, Employee.class);
        repository.save(employee);
        CreateEmployeeResponse data = mapper.forResponse().map(employee, CreateEmployeeResponse.class);

        return new SuccessDataResult<>(data, Messages.Employee.Created);
    }

    @Override
    public DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest request, int id) {
        checkIfEmployeeExistById(id);
        comparePassword(request.getPassword(), request.getConfirmPassword());
        checkIfEmployeeExistByNationalIdentity(request.getNationalIdentity());
        Employee employee = mapper.forRequest().map(request, Employee.class);
        employee.setId(id);
        repository.save(employee);
        UpdateEmployeeResponse data = mapper.forResponse().map(employee, UpdateEmployeeResponse.class);

        return new SuccessDataResult<>(data, Messages.Employee.Updated);
    }

    @Override
    public Result delete(int id) {
        checkIfEmployeeExistById(id);
        repository.deleteById(id);

        return new SuccessResult(Messages.Employee.Deleted);
    }

    @Override
    public Result changePassword(ChangeUserPasswordRequest request, int id) {
        checkIfEmployeeExistById(id);
        checkPasswordIsCorrect(request.getOldPassword(), id);
        checkPasswordIsNew(request.getOldPassword(), request.getNewPassword());
        comparePassword(request.getNewPassword(), request.getConfirmPassword());
        repository.changePassword(request.getNewPassword(), id);

        return new SuccessResult(Messages.User.PasswordChanged);
    }

    @Override
    public void checkIfUserIsEmployee(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Employee.NotAnEmployee);
        }
    }

    private void checkPasswordIsCorrect(String oldPassword, int id) {
        if (!repository.findById(id).get().getPassword().equals(oldPassword)) {
            throw new ValidationException(ValidationMessages.User.OldPasswordNotMatch);
        }
    }

    private void checkPasswordIsNew(String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) {
            throw new ValidationException(ValidationMessages.User.ThereIsNoChangeInPassword);
        }
    }

    private void checkIfEmployeeExistById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Employee.EmployeeNotExists);
        }
    }

    private void checkIfEmployeeExistByNationalIdentity(String nationalIdentity) {
        if (repository.existsEmployeetByNationalIdentity(nationalIdentity)) {
            throw new BusinessException(Messages.Employee.EmployeeExists);
        }
    }

    private void comparePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidationException(ValidationMessages.User.ConfirmPasswordValid);
        }
    }
}
