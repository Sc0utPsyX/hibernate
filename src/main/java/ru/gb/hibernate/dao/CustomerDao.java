package ru.gb.hibernate.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.hibernate.ModelFactory;
import ru.gb.hibernate.model.Customer;

import java.util.List;
import java.util.Objects;

@Component
public class CustomerDao {
    @Autowired
    ModelFactory modelFactory;

    public List<Customer> getAll(){
        Session session = modelFactory.getSession();
        session.beginTransaction();
        List<Customer> customers = session.createQuery("select c from Customer c", Customer.class).getResultList();
        session.getTransaction().commit();
        modelFactory.closeSession(session);
        return customers;
    }

    public Customer getById(Long id){
        Session session = modelFactory.getSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.getTransaction().commit();
        modelFactory.closeSession(session);
        return customer;
    }

    public int deleteById(Long id){
        Session session = modelFactory.getSession();
        session.beginTransaction();
        int result = session.createQuery("delete from Customer c where c.id = :id", Customer.class)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        modelFactory.closeSession(session);
        return result;
    }
    public Customer saveOrUpdate(Customer customer) {
        Session session = modelFactory.getSession();
        session.beginTransaction();
        if (Objects.isNull(customer.getId())) {
            session.persist(customer);
        } else {
            session.merge(customer);
        }
        session.getTransaction().commit();
        modelFactory.closeSession(session);
        return customer;
    }
}

