package ru.gb.hibernate.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.hibernate.ModelFactory;
import ru.gb.hibernate.model.Customer;
import ru.gb.hibernate.model.Product;
import ru.gb.hibernate.model.SavedItem;

import java.util.List;
import java.util.Objects;

@Component
public class SavedItemDao {
    @Autowired
    ModelFactory factory;

    public List<SavedItem> getAll(){
        Session session = factory.getSession();
        session.beginTransaction();
        List<SavedItem> savedItemList = session.createQuery("select s from SavedItem s", SavedItem.class).getResultList();
        session.getTransaction().commit();
        factory.closeSession(session);
        return savedItemList;
    }

    public SavedItem getById(Long id){
        Session session = factory.getSession();
        session.beginTransaction();
        SavedItem savedItem = session.get(SavedItem.class, id);
        session.getTransaction().commit();
        factory.closeSession(session);
        return savedItem;
    }

    public int deleteById(Long id){
        Session session = factory.getSession();
        session.beginTransaction();
        int result = session.createQuery("delete from SavedItem s where s.id = :id", SavedItem.class)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        factory.closeSession(session);
        return result;
    }
    public SavedItem saveOrUpdate(SavedItem savedItem) {
        Session session = factory.getSession();
        session.beginTransaction();
        if (Objects.isNull(savedItem.getId())) {
            session.persist(savedItem);
        } else {
            session.merge(savedItem);
        }
        session.getTransaction().commit();
        factory.closeSession(session);
        return savedItem;
    }

    public List<Product> findCustomerSavedItems(Long customer_id){
        Session session = factory.getSession();
        session.beginTransaction();
        List<Product> productList = session
                .createQuery("select s.product from SavedItem s where s.customer.id = :customer_id", Product.class)
                .setParameter("customer_id", customer_id)
                .getResultList()
                .stream()
                .sorted((s, s1) -> s.getId() > s1.getId() ? 1 : s.getId() < s1.getId() ? -1 : 0)
                .toList();
        session.getTransaction().commit();
        factory.closeSession(session);
        return productList;
    }
    public List<Customer> findProductCustomers(Long product_id){
        Session session = factory.getSession();
        session.beginTransaction();
        List<Customer> customerList = session
                .createQuery("select s.customer from SavedItem s where s.product.id = :product_id", Customer.class)
                .setParameter("product_id", product_id)
                .getResultList()
                .stream()
                .sorted((s, s1) -> s.getId() > s1.getId() ? 1 : s.getId() < s1.getId() ? -1 : 0)
                .toList();
        session.getTransaction().commit();
        factory.closeSession(session);
        return customerList;
    }
}
