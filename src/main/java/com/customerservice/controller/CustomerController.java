package com.customerservice.controller;

import com.customerservice.entity.Customer;
import com.customerservice.service.CustomerService;
import com.customerservice.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<ResponseTemplateVO> findAll(){
        List<ResponseTemplateVO> vo = new ArrayList<>();
        customerService.findAll().forEach(customer -> {
            vo.add(customerService.getCustomerWithFood(customer.getCustomerId()).getBody());
        });
        return vo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVO> getCustomerWithFood(@PathVariable("id") Long id){
        return customerService.getCustomerWithFood(id);
    }

    @Value("${message}")
    private String message;

    @GetMapping("/hello")
    public String hello(){
        return message;
    }
}