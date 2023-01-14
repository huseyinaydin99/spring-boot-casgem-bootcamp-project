package tr.com.huseyinaydin.casgem.bootcamp.business.concretes.applications;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.applications.ApplicationService;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.blacklists.BlacklistService;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.bootcamps.BootcampService;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users.ApplicantService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Messages;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.application.CreateApplicationRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.application.UpdateApplicationRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.application.CreateApplicationResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.applications.GetAllApplicationsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.applications.GetApplicationResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.application.UpdateApplicationResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.exceptions.BusinessException;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.mapping.ModelMapperService;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessDataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessResult;
import tr.com.huseyinaydin.casgem.bootcamp.entities.applications.Application;
import tr.com.huseyinaydin.casgem.bootcamp.entities.applications.State;
import tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.applications.ApplicationRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationManager implements ApplicationService {
    private final ApplicationRepository repository;
    private final BootcampService bootcampService;
    private final ApplicantService applicantService;
    private final BlacklistService blacklistService;
    private final ModelMapperService mapper;

    @Override
    public DataResult<List<GetAllApplicationsResponse>> getAll() {
        List<Application> applications = repository.findAll();
        List<GetAllApplicationsResponse> data = applications
                .stream()
                .map(application -> mapper.forResponse().map(application, GetAllApplicationsResponse.class))
                .toList();

        return new SuccessDataResult<>(data, Messages.Application.ListAll);
    }

    @Override
    public DataResult<GetApplicationResponse> getById(int id) {
        checkIfApplicationExistById(id);
        Application application = repository.findById(id).orElseThrow();
        GetApplicationResponse data = mapper.forResponse().map(application, GetApplicationResponse.class);

        return new SuccessDataResult<>(data, Messages.Application.ListById);
    }

    @Override
    public DataResult<CreateApplicationResponse> add(CreateApplicationRequest request) {
        bootcampService.checkIfBootcampExistById(request.getBootcampId());
        applicantService.checkIfApplicantExistById(request.getApplicantId());
        blacklistService.checkIfApplicantInBlacklist(request.getApplicantId());
        checkIfUserHasApplication(request.getApplicantId());
        bootcampService.checkIfBootcampIsActive(request.getBootcampId());
        Application application = mapper.forRequest().map(request, Application.class);
        State state = mapper.forResponse().map(application, State.class);
        state.setId(3); // Default state is "Pending"
        application.setState(state);
        application.setId(0);
        repository.save(application);
        CreateApplicationResponse data = mapper.forResponse().map(application, CreateApplicationResponse.class);

        return new SuccessDataResult<>(data, Messages.Application.Created);
    }

    @Override
    public DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest request, int id) {
        checkIfApplicationExistById(id);
        bootcampService.checkIfBootcampExistById(request.getBootcampId());
        applicantService.checkIfApplicantExistById(request.getApplicantId());
        bootcampService.checkIfBootcampIsActive(request.getBootcampId());
        Application application = mapper.forRequest().map(request, Application.class);
        application.setId(id);
        repository.save(application);
        UpdateApplicationResponse data = mapper.forResponse().map(application, UpdateApplicationResponse.class);

        return new SuccessDataResult<>(data, Messages.Application.Updated);
    }

    @Override
    public Result delete(int id) {
        checkIfApplicationExistById(id);
        repository.deleteById(id);

        return new SuccessResult(Messages.Application.Deleted);
    }

    @Override
    public Result findApplicationAndDeleteFromApplication(int applicantId) {
        Application application = repository.findApplicationByApplicantId(applicantId);
        if (repository.existsApplicationsByApplicantId(applicantId)) {
            repository.deleteById(application.getId());

            return new SuccessResult(Messages.Blacklist.RemovedFromApplication);
        }

        return new SuccessResult(Messages.Blacklist.Blank);
    }

    private void checkIfUserHasApplication(int userId) {
        if (repository.existsApplicationsByApplicantId(userId)) {
            throw new BusinessException(Messages.Application.UserHasApplication);
        }
    }

    private void checkIfApplicationExistById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Application.ApplicationNotExists);
        }
    }
}