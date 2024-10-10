package com.thomasaleknovic.workestimateapi.services;

import com.thomasaleknovic.workestimateapi.dtos.CustomerDTO;
import com.thomasaleknovic.workestimateapi.exceptions.Customer.CustomerNotFoundException;
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
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer createCustomer(CustomerDTO data) {
        Customer customer = new Customer(data);
        return customerRepository.save(customer);
    }

    public Customer updateCustomerInfo(UUID id, CustomerDTO data) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);

        if (data.name() != null && !data.name().equals(customer.getName())) {
            customer.setName(data.name());
        }

        if (data.cpf() != null && !data.cpf().equals(customer.getCpf())) {
            customer.setCpf(data.cpf());
        }

        if (data.email() != null && !data.email().equals(customer.getEmail())) {
            customer.setEmail(data.email());
        }

        if (data.phone() != null && !data.phone().equals(customer.getPhone())) {
            customer.setPhone(data.phone());
        }

        if (data.address() != null && !data.address().equals(customer.getAddress())) {
            customer.setAddress(data.address());
        }

        if (data.city() != null && !data.city().equals(customer.getCity())) {
            customer.setCity(data.city());
        }

        if (data.state() != null && !data.state().equals(customer.getState())) {
            customer.setState(data.state());
        }

        if (data.zipCode() != null && !data.zipCode().equals(customer.getZipCode())) {
            customer.setZipCode(data.zipCode());
        }

        if (data.country() != null && !data.country().equals(customer.getCountry())) {
            customer.setCountry(data.country());
        }

        return customerRepository.save(customer);
    }


    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        customerRepository.deleteById(customer.getCustomerId());
    }
}
