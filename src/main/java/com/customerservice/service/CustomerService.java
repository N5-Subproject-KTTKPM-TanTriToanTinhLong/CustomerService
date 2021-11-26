package com.customerservice.service;

import com.customerservice.entity.Customer;
import com.customerservice.entity.Food;
import com.customerservice.entity.ExceptionHandling;
import com.customerservice.repository.CustomerRepository;
import com.customerservice.vo.ResponseTemplateVO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Retry(name = "basic")
 //   @RateLimiter(name = "multiRate", fallbackMethod = "fallbackMethod")
    public ResponseEntity<ResponseTemplateVO> getCustomerWithOrder(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Customer customer = customerRepository.findById(userId).get();
        vo.setCustomer(customer);
        Food food = restTemplate.getForObject("http://localhost:8080/food/"
                                + customer.getFoodId(),
                        Food.class);

        vo.setFood(food);
        return new ResponseEntity<ResponseTemplateVO>(vo, HttpStatus.OK);
    }

    private ResponseEntity<ExceptionHandling> fallBackMethod(RuntimeException exception){
//        return new ResponseTemplateVO(new User(), new Order());
        ExceptionHandling handling = new ExceptionHandling("Quá nhiều yêu cầu xin hãy reload lại vài giây sau");
        return new ResponseEntity<ExceptionHandling>(handling, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
