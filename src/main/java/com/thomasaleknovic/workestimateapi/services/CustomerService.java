package com.thomasaleknovic.workestimateapi.services;

import com.thomasaleknovic.workestimateapi.dtos.CustomerDTO;
import com.thomasaleknovic.workestimateapi.models.Customer;
import com.thomasaleknovic.workestimateapi.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomer(UUID id) {
        return customerRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Customer not found"));
    }

    public Customer createCustomer(CustomerDTO data) {
        Customer customer = new Customer(data);
        return customerRepository.save(customer);
    }

    public Customer updateCustomerInfo(UUID id, CustomerDTO data) {
        Customer customer = findCustomer(id);
        customer.setName(data.name());
        customer.setCpf(data.cpf());
        customer.setEmail(data.email());
        customer.setPhone(data.phone());
        customer.setAddress(data.address());
        customer.setCity(data.city());
        customer.setState(data.state());
        customer.setZipCode(data.zipCode());
        customer.setCountry(data.country());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }
}
