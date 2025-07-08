package com.ecommers.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="colour")
public class Colour implements java.io.Serializable {

    private int colourId;
    private String colourName;
    private Set<Product> products = new HashSet<>();

    public Colour() {}

    public Colour(int colourId, String colourName) {
        this.colourId = colourId;
        this.colourName = colourName;
    }

    public Colour(int colourId, String colourName, Set<Product> products) {
        this.colourId = colourId;
        this.colourName = colourName;
        this.products = products;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "colour_id", unique = true, nullable = false)
    public int getColourId() {
        return this.colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }

    @Column(name = "colour_name", nullable = false, length = 100)
    public String getColourName() {
        return this.colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "colours")
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
