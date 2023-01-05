package ru.gb.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.hibernate.dao.ProductDao;
import ru.gb.hibernate.model.Customer;
import ru.gb.hibernate.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<Product> deleteProduct(Long productId){
        productDao.deleteById(productId);
        return productDao.getAll();
    }

    public Product findById(Long productId){
        return productDao.getById(productId);
    }

    public Product saveProduct(Product product){
        return productDao.saveOrUpdate(product);
    }


    public List<Product> productSubmit(String productTitle, BigDecimal productCost) {
        productDao.saveOrUpdate(new Product(productTitle, productCost));
        return productDao.getAll();
    }

}
