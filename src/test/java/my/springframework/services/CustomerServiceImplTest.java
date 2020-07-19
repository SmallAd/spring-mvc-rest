package my.springframework.services;

import my.springframework.api.v1.mapper.CustomerMapper;
import my.springframework.api.v1.model.CustomerDTO;
import my.springframework.domain.Customer;
import my.springframework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    public static final Long ID = 42L;
    public static final String FIRST_NAME = "Vasya";
    public static final String LAST_NAME = "Pupkin";
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {

        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer(), new Customer()));

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertNotNull(customerDTOS);
        assertEquals(3, customerDTOS.size());
    }

    @Test
    void getById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getById(ID);

        assertNotNull(customerDTO);
        assertEquals(FIRST_NAME, customerDTO.getFirstname());
        assertEquals(LAST_NAME, customerDTO.getLastname());
    }

    @Test
    void createNewCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRST_NAME);
        customerDTO.setLastname(LAST_NAME);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

        assertNotNull(savedDTO);
        assertEquals(FIRST_NAME, savedDTO.getFirstname());
        assertEquals(LAST_NAME, savedDTO.getLastname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
    }
}