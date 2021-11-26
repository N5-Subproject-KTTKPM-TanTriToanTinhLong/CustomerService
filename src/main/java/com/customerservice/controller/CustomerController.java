package com.customerservice.controller;

import com.customerservice.entity.Customer;
import com.customerservice.service.CustomerService;
import com.customerservice.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping()
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getCustomerWithFood(@PathVariable("id") Long id){
        return customerService.getCustomerWithFood(id);
    }
}