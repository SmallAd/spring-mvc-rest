package my.springframework.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.springframework.domain.Category;
import my.springframework.domain.Customer;
import my.springframework.domain.Vendor;
import my.springframework.repositories.CategoryRepository;
import my.springframework.repositories.CustomerRepository;
import my.springframework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    @Override
    public void run(String... args) {
        loadCatigories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor1");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor2");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Vendor3");

        Vendor vendor4 = new Vendor();
        vendor4.setName("Vendor4");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
        vendorRepository.save(vendor4);

        log.info("Vendor data loaded = " + vendorRepository.count());
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Ivan");
        customer1.setLastName("Ivanov");

        Customer customer2 = new Customer();
        customer2.setFirstName("Petr");
        customer2.setLastName("Petrov");

        Customer customer3 = new Customer();
        customer3.setFirstName("Sidor");
        customer3.setLastName("Sidorov");

        Customer customer4 = new Customer();
        customer4.setFirstName("John");
        customer4.setLastName("Smith");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);

        log.info("Customer data loaded = " + customerRepository.count());
    }

    private void loadCatigories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        log.info("Category data loaded = " + categoryRepository.count());
    }
}
