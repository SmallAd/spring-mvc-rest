package my.springframework.services;

import my.springframework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}
