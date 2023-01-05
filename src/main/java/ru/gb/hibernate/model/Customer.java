package ru.gb.hibernate.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "customers")
public class Customer {
    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy ="customer")
    private List<SavedItem> savedItemList;

    public Customer(String name) {
        this.name = name;
    }

    public List<SavedItem> getSavedItemList() {
        return savedItemList;
    }

    public void setSavedItemList(List<SavedItem> savedItemList) {
        this.savedItemList = savedItemList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\''
                + "}\n";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
