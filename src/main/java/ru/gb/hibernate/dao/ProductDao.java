package ru.gb.hibernate.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.hibernate.ModelFactory;
import ru.gb.hibernate.model.Product;

import java.util.List;
import java.util.Objects;

@Component
public class ProductDao {
    @Autowired
    ModelFactory factory;

    public List<Product> getAll(){
        Session session = factory.getSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("select p from Product p", Product.class).getResultList();
        session.getTransaction().commit();
        factory.closeSession(session);
        return products;
    }

    public Product getById(Long id){
        Session session = factory.getSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        factory.closeSession(session);
        return product;
    }

    public int deleteById(Long id){
        Session session = factory.getSession();
        session.beginTransaction();
        int result = session.createQuery("delete from Product p where p.id = :id", Product.class)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        factory.closeSession(session);
        return result;
    }
    public Product saveOrUpdate(Product product) {
        Session session = factory.getSession();
        session.beginTransaction();
        if (Objects.isNull(product.getId())) {
            session.persist(product);
        } else {
            session.merge(product);
        }
        session.getTransaction().commit();
        factory.closeSession(session);
        return product;
    }
}
