package com.customerservice.vo;

import com.customerservice.entity.Customer;
import com.customerservice.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Customer customer;

    private Food food;

}
