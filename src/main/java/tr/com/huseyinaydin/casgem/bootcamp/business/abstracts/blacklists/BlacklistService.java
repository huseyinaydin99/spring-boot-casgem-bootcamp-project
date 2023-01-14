package tr.com.huseyinaydin.casgem.bootcamp.business.abstracts.blacklists;

import java.util.List;

import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.create.blacklist.CreateBlacklistRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.requests.update.blacklist.UpdateBlacklistRequest;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.create.blacklist.CreateBlacklistResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.blacklists.GetAllBlacklistsResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.get.blacklists.GetBlacklistResponse;
import tr.com.huseyinaydin.casgem.bootcamp.business.dto.responses.update.blacklist.UpdateBlacklistResponse;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.DataResult;
import tr.com.huseyinaydin.casgem.bootcamp.core.utils.results.Result;

public interface BlacklistService {
    DataResult<List<GetAllBlacklistsResponse>> getAll();
    DataResult<GetBlacklistResponse> getById(int id);
    DataResult<CreateBlacklistResponse> add(CreateBlacklistRequest request);
    DataResult<UpdateBlacklistResponse> update(UpdateBlacklistRequest request, int id);
    Result delete(int id);
    void checkIfApplicantInBlacklist(int applicantId);
}
