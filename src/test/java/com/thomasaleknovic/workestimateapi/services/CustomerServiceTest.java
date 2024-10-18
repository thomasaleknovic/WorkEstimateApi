package com.thomasaleknovic.workestimateapi.services;

import com.thomasaleknovic.workestimateapi.exceptions.Customer.CustomerNotFoundException;
import com.thomasaleknovic.workestimateapi.exceptions.Estimate.EstimateNotFoundException;
import com.thomasaleknovic.workestimateapi.models.Customer;
import com.thomasaleknovic.workestimateapi.repository.CustomerRepository;
import com.thomasaleknovic.workestimateapi.utils.MockCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.thomasaleknovic.workestimateapi.utils.MockCompany.mockCompanyEntity;
import static com.thomasaleknovic.workestimateapi.utils.MockCustomer.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CompanyService companyService;

    @InjectMocks
    @Autowired
    CustomerService customerService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a Customer by it's Id.")
    void testFindCustomerSuccess(){

        when(customerRepository.findById(any())).thenReturn(Optional.of(mockCustomerEntity()));

        Customer result = customerService.findCustomer(mockCustomerEntity().getCustomerId());

        assertNotNull(result);
        assertEquals(CUSTOMER_ID, result.getCustomerId());

    }

    @Test
    @DisplayName("Should throw an exception when Customer don't exists.")
    void testFindCustomerFail(){
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.findCustomer(mockCustomerEntity().getCustomerId());
        });
    }

    @Test
    @DisplayName("Should create a new Customer.")
    void testCreateCustomerSuccess(){

        when(customerRepository.save(any())).thenReturn(mockCustomerEntity());
        when(companyService.findCompanyById(any())).thenReturn(mockCompanyEntity());

        Customer result = customerService.createCustomer(mockCustomerDTO());

        assertNotNull(result);
        assertEquals(mockCustomerEntity().getCpf(), result.getCpf());
    }
}
