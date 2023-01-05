package ru.gb.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.hibernate.dao.ProductDao;
import ru.gb.hibernate.dao.SavedItemDao;
import ru.gb.hibernate.model.Customer;
import ru.gb.hibernate.model.Product;
import ru.gb.hibernate.model.SavedItem;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SavedItemsService {

    @Autowired
    private SavedItemDao savedItemDao;

    public List<SavedItem> getAllItems() {
        return savedItemDao.getAll();
    }

    public List<SavedItem> deleteItems(Long itemId){
        savedItemDao.deleteById(itemId);
        return savedItemDao.getAll();
    }

    public SavedItem saveItem(SavedItem savedItem){
        return savedItemDao.saveOrUpdate(savedItem);
    }


    public List<Product> findCustomerSavedItems(Long customer_id){
        return savedItemDao.findCustomerSavedItems(customer_id);
    }

    public List<Customer> findProductCustomers(Long product_id){
        return savedItemDao.findProductCustomers(product_id);
    }

    public List<SavedItem> savedItemSubmit(Customer customer, Product product, int qty) {
        savedItemDao.saveOrUpdate(new SavedItem(customer, product, qty));
        return savedItemDao.getAll();
    }

}
