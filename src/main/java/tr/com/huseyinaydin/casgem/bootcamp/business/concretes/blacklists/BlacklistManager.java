package tr.com.huseyinaydin.casgem.bootcamp.business.concretes.blacklists;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.applications.ApplicationService;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.blacklists.BlacklistService;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users.ApplicantService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Messages;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.blacklist.CreateBlacklistRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.blacklist.UpdateBlacklistRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.blacklist.CreateBlacklistResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.blacklists.GetAllBlacklistsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.blacklists.GetBlacklistResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.blacklist.UpdateBlacklistResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.exceptions.BusinessException;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.mapping.ModelMapperService;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessDataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessResult;
import tr.com.huseyinaydin.casgem.bootcamp.entities.blacklists.Blacklist;
import tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.blacklists.BlacklistRepository;

import java.util.List;

@Service
public class BlacklistManager implements BlacklistService {
    private final BlacklistRepository repository;
    private final ModelMapperService mapper;
    private final ApplicantService applicantService;
    private final ApplicationService applicationService;

    public BlacklistManager(
            BlacklistRepository repository,
            ModelMapperService mapper,
            ApplicantService applicantService,
            @Lazy ApplicationService applicationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.applicantService = applicantService;
        this.applicationService = applicationService;
    }

    @Override
    public DataResult<List<GetAllBlacklistsResponse>> getAll() {
        List<Blacklist> blacklists = repository.findAll();
        List<GetAllBlacklistsResponse> data = blacklists
                .stream()
                .map(blacklist -> mapper.forResponse().map(blacklist, GetAllBlacklistsResponse.class))
                .toList();

        return new SuccessDataResult<>(data, Messages.Blacklist.ListAll);
    }

    @Override
    public DataResult<GetBlacklistResponse> getById(int id) {
        checkIfBlacklistExistById(id);
        Blacklist blacklist = repository.findById(id).orElseThrow();
        GetBlacklistResponse data = mapper.forResponse().map(blacklist, GetBlacklistResponse.class);

        return new SuccessDataResult<>(data, Messages.Blacklist.ListById);
    }

    @Override
    public DataResult<CreateBlacklistResponse> add(CreateBlacklistRequest request) {
        applicantService.checkIfApplicantExistById(request.getApplicantId());
        checkIfApplicantInBlacklist(request.getApplicantId());
        Blacklist blacklist = mapper.forRequest().map(request, Blacklist.class);
        blacklist.setId(0);
        repository.save(blacklist);
        Result result = applicationService.findApplicationAndDeleteFromApplication(request.getApplicantId());
        CreateBlacklistResponse data = mapper.forResponse().map(blacklist, CreateBlacklistResponse.class);
        String message = Messages.Blacklist.Created + result.getMessage();

        return new SuccessDataResult<>(data, message);
    }

    @Override
    public DataResult<UpdateBlacklistResponse> update(UpdateBlacklistRequest request, int id) {
        checkIfBlacklistExistById(id);
        applicantService.checkIfApplicantExistById(request.getApplicantId());
        Blacklist blacklist = mapper.forRequest().map(request, Blacklist.class);
        blacklist.setId(id);
        repository.save(blacklist);
        UpdateBlacklistResponse data = mapper.forResponse().map(blacklist, UpdateBlacklistResponse.class);

        return new SuccessDataResult<>(data, Messages.Blacklist.Updated);
    }

    @Override
    public Result delete(int id) {
        checkIfBlacklistExistById(id);
        repository.deleteById(id);

        return new SuccessResult(Messages.Blacklist.Deleted);
    }

    @Override
    public void checkIfApplicantInBlacklist(int id) {
        if (repository.existsBlacklistByApplicantId(id)) {
            throw new BusinessException(Messages.Blacklist.ApplicantInBlacklist);
        }
    }

    private void checkIfBlacklistExistById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Blacklist.BlacklistNotExist);
        }
    }
}
