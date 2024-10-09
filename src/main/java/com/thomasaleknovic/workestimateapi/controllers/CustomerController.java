package com.thomasaleknovic.workestimateapi.controllers;

import com.thomasaleknovic.workestimateapi.dtos.CustomerDTO;
import com.thomasaleknovic.workestimateapi.models.Customer;
import com.thomasaleknovic.workestimateapi.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
public class CustomerController {


    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> listAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomer(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.findCustomer(id));
    }

    @PostMapping("/new")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO data) {
        return ResponseEntity.ok(customerService.createCustomer(data));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id, @RequestBody CustomerDTO data) {
        return ResponseEntity.ok(customerService.updateCustomerInfo(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }



}
