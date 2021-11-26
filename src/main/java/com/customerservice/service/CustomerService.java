package com.customerservice.service;

import com.customerservice.entity.Customer;
import com.customerservice.entity.Food;
import com.customerservice.repository.CustomerRepository;
import com.customerservice.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Customer save(Customer user) {
        return customerRepository.save(user);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public ResponseTemplateVO getCustomerWithFood(Long id) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Customer customer = customerRepository.findById(id).get();
        vo.setCustomer(customer);
        Food food = restTemplate.getForObject("http://localhost:8080/api/food/"
                                + customer.getFoodId(),
                        Food.class);

        vo.setFood(food);
        return vo;
    }
}
