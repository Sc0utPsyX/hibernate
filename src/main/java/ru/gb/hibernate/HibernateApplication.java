package ru.gb.hibernate;

import com.github.javafaker.Faker;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.hibernate.model.Customer;
import ru.gb.hibernate.model.Product;
import ru.gb.hibernate.model.SavedItem;

import java.math.BigDecimal;
import java.util.List;


@SpringBootApplication
public class HibernateApplication {
    public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.gb.hibernate");
    ProductService productService = context.getBean(ProductService.class);
    CustomerService customerService = context.getBean(CustomerService.class);
    SavedItemsService savedItemsService = context.getBean(SavedItemsService.class);
        Faker faker = new Faker(); // уж очень мне Faker понравился ;)
        for (int i = 0; i < 10; i++) {
            productService.saveProduct(new Product(faker.food().ingredient(),
                    new BigDecimal(faker.number().randomDouble(2,1, 1000))));
            customerService.saveCustomer(new Customer(faker.name().fullName()));
        }
        for (int i = 0; i < 30; i++) {
            savedItemsService.saveItem(
                    new SavedItem(customerService.findById(faker.number().numberBetween(1L, 10L)),
                            productService.findById(faker.number().numberBetween(1L, 10L)),
                            faker.number().numberBetween(1, 10)));
        }
        System.out.println("Hello");
        List<Customer> customerList = savedItemsService.findProductCustomers(1L);
        List<Product> productList = savedItemsService.findCustomerSavedItems(3L);
        System.out.println("Product with id 1 bought:");
        System.out.println(customerList);
        System.out.println("Customer with id 3 has basket with:");
        System.out.println(productList);
    }
}
