package ru.gb.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.hibernate.dao.CustomerDao;
import ru.gb.hibernate.dao.ProductDao;
import ru.gb.hibernate.model.Customer;
import ru.gb.hibernate.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getAllCustomers() {
        return customerDao.getAll();
    }

    public List<Customer> deleteCustomer(Long customerId){
        customerDao.deleteById(customerId);
        return customerDao.getAll();
    }

    public Customer findById(Long customerId){
        return customerDao.getById(customerId);
    }

    public Customer saveCustomer(Customer customer){
        return customerDao.saveOrUpdate(customer);
    }


    public List<Customer> productSubmit(String customerName) {
        customerDao.saveOrUpdate(new Customer(customerName));
        return customerDao.getAll();
    }

}
