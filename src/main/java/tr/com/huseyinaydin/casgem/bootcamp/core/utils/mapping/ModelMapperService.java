package tr.com.huseyinaydin.casgem.bootcamp.core.utils.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
