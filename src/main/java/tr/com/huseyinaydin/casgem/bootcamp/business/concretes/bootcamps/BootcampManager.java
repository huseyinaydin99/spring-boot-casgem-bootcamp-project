package tr.com.huseyinaydin.casgem.bootcamp.business.concretes.bootcamps;

import lombok.AllArgsConstructor;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.bootcamps.BootcampService;
import tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.users.InstructorService;
import tr.com.huseyinaydin.casgem.bootcamp.business.constants.Messages;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.bootcamp.CreateBootcampRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.bootcamp.UpdateBootcampRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.bootcamp.CreateBootcampResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.bootcamps.GetAllBootcampsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.bootcamps.GetBootcampResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.bootcamp.UpdateBootcampResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.exceptions.BusinessException;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.mapping.ModelMapperService;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessDataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.SuccessResult;
import tr.com.huseyinaydin.casgem.bootcamp.entities.bootcamps.Bootcamp;
import tr.com.huseyinaydin.casgem.bootcamp.repository.abstracts.bootcamps.BootcampRepository;

import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BootcampManager implements BootcampService {
    private final BootcampRepository repository;
    private final InstructorService instructorService;
    private final ModelMapperService mapper;

    @Override
    public DataResult<List<GetAllBootcampsResponse>> getAll() {

        List<Bootcamp> bootcamps = repository.findAll();
        List<GetAllBootcampsResponse> data = bootcamps
                .stream()
                .map(bootcamp -> mapper.forResponse().map(bootcamp, GetAllBootcampsResponse.class))
                .toList();

        return new SuccessDataResult<>(data, Messages.Bootcamp.ListAll);
    }

    @Override
    public DataResult<GetBootcampResponse> getById(int id) {
        checkIfBootcampExistById(id);
        Bootcamp bootcamp = repository.findById(id).orElseThrow();
        GetBootcampResponse data = mapper.forResponse().map(bootcamp, GetBootcampResponse.class);

        return new SuccessDataResult<>(data, Messages.Bootcamp.ListById);
    }

    @Override
    public DataResult<CreateBootcampResponse> add(CreateBootcampRequest request) {
        instructorService.checkIfInstructorExistById(request.getInstructorId());
        checkIfStartDateBiggerThanEndDate(request.getStartDate(), request.getEndDate());
        Bootcamp bootcamp = mapper.forRequest().map(request, Bootcamp.class);
        bootcamp.setId(0);
        repository.save(bootcamp);
        CreateBootcampResponse data = mapper.forResponse().map(bootcamp, CreateBootcampResponse.class);

        return new SuccessDataResult<>(data, Messages.Bootcamp.Created);
    }

    @Override
    public DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest request, int id) {
        checkIfBootcampExistById(id);
        instructorService.checkIfInstructorExistById(request.getInstructorId());
        checkIfStartDateBiggerThanEndDate(request.getStartDate(), request.getEndDate());
        Bootcamp bootcamp = mapper.forRequest().map(request, Bootcamp.class);
        bootcamp.setId(id);
        repository.save(bootcamp);
        UpdateBootcampResponse data = mapper.forResponse().map(bootcamp, UpdateBootcampResponse.class);

        return new SuccessDataResult<>(data, Messages.Bootcamp.Updated);
    }

    @Override
    public Result delete(int id) {
        checkIfBootcampExistById(id);
        repository.deleteById(id);

        return new SuccessResult(Messages.Bootcamp.Deleted);
    }

    @Override
    public void checkIfBootcampIsActive(int bootcampId) {
        checkIfBootcampExistById(bootcampId);
        Bootcamp bootcamp = repository.findById(bootcampId).orElseThrow();
        if (bootcamp.getState() == 2) {
            throw new BusinessException(Messages.Bootcamp.BootcampIsNotActive);
        }
    }

    @Override
    public void checkIfBootcampExistById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Bootcamp.BootcampNotExists);
        }
    }

    private void checkIfStartDateBiggerThanEndDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new ValidationException(Messages.Bootcamp.StartDateBigThanEndDate);
        }
    }
}
