package my.springframework.api.v1.mapper;

import my.springframework.api.v1.model.CustomerDTO;
import my.springframework.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final Long ID = 3L;
    public static final String FIRST_NAME = "Billy";
    public static final String LAST_NAME = "Bones";

    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        CustomerMapper customerMapper = CustomerMapper.INSTANCE;

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertNotNull(customerDTO);
        assertEquals(FIRST_NAME, customerDTO.getFirstname());
        assertEquals(LAST_NAME, customerDTO.getLastname());
    }
}