package com.example.productmanager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "retailers")
@Setter
@Getter
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_retailer")
    private int id;

    @Column(name = "retailer_name", updatable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "retailers_products",
            joinColumns = @JoinColumn(name = "id_retailer"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    @JsonBackReference
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Retailer retailer = (Retailer) o;
        return id == retailer.id && Objects.equals(name, retailer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Retailer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
