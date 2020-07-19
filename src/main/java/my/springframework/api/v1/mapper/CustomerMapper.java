package my.springframework.api.v1.mapper;

import my.springframework.api.v1.model.CustomerDTO;
import my.springframework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(source = "firstName", target = "firstname"),
            @Mapping(source = "lastName", target = "lastname")
    })
    CustomerDTO customerToCustomerDTO(Customer customer);
}
