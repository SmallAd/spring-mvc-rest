package my.springframework.services;

import lombok.RequiredArgsConstructor;
import my.springframework.api.v1.mapper.CustomerMapper;
import my.springframework.api.v1.model.CustomerDTO;
import my.springframework.domain.Customer;
import my.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    public static final String CUSTOMERS_URL = "/api/v1/customers/";
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(CUSTOMERS_URL + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(customer -> {
                    if (customerDTO.getFirstname() != null) {
                        customer.setFirstName(customerDTO.getFirstname());
                    }

                    if (customerDTO.getLastname() != null) {
                        customer.setLastName(customerDTO.getLastname());
                    }
                    CustomerDTO savedDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));

                    savedDTO.setCustomerUrl(CUSTOMERS_URL + id);
                    return savedDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl(CUSTOMERS_URL + savedCustomer.getId());

        return returnDTO;
    }
}
