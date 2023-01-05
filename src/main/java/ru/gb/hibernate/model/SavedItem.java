package ru.gb.hibernate.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "SavedItem")
public class SavedItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Customer customer;

        @ManyToOne
        private Product product;

        private BigDecimal currentCost;

        private Integer qty;


        public SavedItem() {
        }

    @Override
    public String toString() {
        return "SavedItem{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", currentCost=" + currentCost +
                ", qty=" + qty +
                "}\n";
    }

    public SavedItem(Customer customer, Product product, Integer qty) {
            this.customer = customer;
            this.product = product;
            this.currentCost = product.getCost(); // вот это для 4 задания сохраняю текущую стоимость на момент добавления в "корзину"
            this.qty = qty;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public BigDecimal getCurrentCost() {
            return currentCost;
        }

        public void setCurrentCost(BigDecimal currentCost) {
            this.currentCost = currentCost;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

}
