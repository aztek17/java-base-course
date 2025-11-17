package ru.base.shop.service;

import ru.base.shop.model.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    Customer getCustomerById(int id);

    List<Customer> getAllCustomer();

    Customer getCustomerByDate(Date date);

    void removeCustomerById(int id);

    void removeAllCustomer();

}
