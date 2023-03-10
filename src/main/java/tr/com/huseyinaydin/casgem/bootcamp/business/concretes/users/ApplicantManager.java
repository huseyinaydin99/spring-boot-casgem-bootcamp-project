package tr.com.huseyinaydin.casgem.bootcamp.business.concretes.users;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users.ApplicantService;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users.EmployeeService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Messages;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.ValidationMessages;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.users.applicant.CreateApplicantRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.ChangeUserPasswordRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.users.applicant.UpdateApplicantRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.users.applicant.CreateApplicantResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.applicants.GetAllApplicantsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.users.applicants.GetApplicantResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.users.applicant.UpdateApplicantResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.exceptions.BusinessException;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.mapping.ModelMapperService;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessDataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessResult;
import tr.com.huseyinaydin.casgem.bootcamp.entities.users.Applicant;
import tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.users.ApplicantRepository;

import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicantManager implements ApplicantService {
    private ApplicantRepository repository;
    private EmployeeService employeeService;
    private ModelMapperService mapper;

    @Override
    public DataResult<List<GetAllApplicantsResponse>> getAll() {
        List<Applicant> appliicants = repository.findAll();
        List<GetAllApplicantsResponse> data = appliicants
                .stream()
                .map(applicant -> mapper.forResponse().map(applicant, GetAllApplicantsResponse.class))
                .toList();

        return new SuccessDataResult<>(data, Messages.Applicant.ListAll);
    }

    @Override
    public DataResult<GetApplicantResponse> getById(int id) {
        checkIfApplicantExistById(id);
        Applicant appliicant = repository.findById(id).orElse(null);
        GetApplicantResponse data = mapper.forResponse().map(appliicant, GetApplicantResponse.class);

        return new SuccessDataResult<>(data, Messages.Applicant.ListById);
    }

    @Override
    public DataResult<CreateApplicantResponse> add(CreateApplicantRequest request) {
        comparePassword(request.getPassword(), request.getConfirmPassword());
        checkIfApplicantExistByNationalIdentity(request.getNationalIdentity());
        Applicant appliicant = mapper.forRequest().map(request, Applicant.class);
        repository.save(appliicant);
        CreateApplicantResponse data = mapper.forResponse().map(appliicant, CreateApplicantResponse.class);

        return new SuccessDataResult<>(data, Messages.Applicant.Created);
    }

    @Override
    public DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest request, int id) {
        checkIfApplicantExistById(id);
        comparePassword(request.getPassword(), request.getConfirmPassword());
        checkIfApplicantExistByNationalIdentity(request.getNationalIdentity());
        Applicant applicant = mapper.forRequest().map(request, Applicant.class);
        applicant.setId(id);
        repository.save(applicant);
        UpdateApplicantResponse data = mapper.forResponse().map(applicant, UpdateApplicantResponse.class);

        return new SuccessDataResult<>(data, Messages.Applicant.Updated);
    }

    @Override
    public Result delete(int id) {
        checkIfApplicantExistById(id);
        repository.deleteById(id);

        return new SuccessResult(Messages.Applicant.Deleted);
    }

    @Override
    public DataResult<GetApplicantResponse> beAnApplicant(String about, int id) {
        checkIfAlreadyAnApplicant(id);
        checkIfAboutValid(about);
        Applicant applicant = mapper.forResponse().map(employeeService.getById(id).getData(), Applicant.class);
        applicant.setAbout(about);
        repository.beAnApplicant(about, id);
        GetApplicantResponse data = mapper.forResponse().map(applicant, GetApplicantResponse.class);

        return new SuccessDataResult<>(data, Messages.Applicant.BecameApplicant);
    }

    @Override
    public Result removeAnApplicant(int id) {
        checkIfApplicantExistById(id);
        employeeService.checkIfUserIsEmployee(id);
        repository.removeApplicantFromApplication(id);
        repository.removeApplicantFromBlacklist(id);
        repository.removeAnApplicant(id);

        return new SuccessResult(Messages.Applicant.Deleted);
    }

    @Override
    public Result changePassword(ChangeUserPasswordRequest request, int id) {
        checkIfApplicantExistById(id);
        checkPasswordIsCorrect(request.getOldPassword(), id);
        checkPasswordIsNew(request.getOldPassword(), request.getNewPassword());
        comparePassword(request.getNewPassword(), request.getConfirmPassword());
        repository.changePassword(request.getNewPassword(), id);

        return new SuccessResult(Messages.User.PasswordChanged);
    }

    @Override
    public void checkIfApplicantExistById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Applicant.ApplicantNotExists);
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

    private void checkIfAlreadyAnApplicant(int id) {
        if (repository.findById(id).isPresent()) {
            throw new BusinessException(Messages.Applicant.AlreadyApplicant);
        }
    }

    private void checkIfApplicantExistByNationalIdentity(String nationalIdentity) {
        if (repository.existsApplicantByNationalIdentity(nationalIdentity)) {
            throw new BusinessException(Messages.Applicant.ApplicantExists);
        }
    }

    private void checkIfAboutValid(String about) {
        if (about.length() <= 5 || about.length() >= 50) {
            throw new ValidationException(Messages.Applicant.AboutValid);
        }
    }

    private void comparePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidationException(ValidationMessages.User.ConfirmPasswordValid);
        }
    }
}
