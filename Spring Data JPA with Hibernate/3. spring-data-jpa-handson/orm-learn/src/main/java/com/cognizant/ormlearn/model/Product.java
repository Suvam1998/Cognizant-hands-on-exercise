package com.cognizant.ormlearn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Product for the Criteria Query dynamic-filter demo (Hands-on 6). */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int id;

    @Column(name = "p_name")
    private String name;

    @Column(name = "p_category")
    private String category;

    @Column(name = "p_ram")
    private int ram;          // GB

    @Column(name = "p_cpu_speed")
    private double cpuSpeed;  // GHz

    @Column(name = "p_rating")
    private double rating;    // customer review

    @Column(name = "p_os")
    private String os;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getRam() { return ram; }
    public void setRam(int ram) { this.ram = ram; }
    public double getCpuSpeed() { return cpuSpeed; }
    public void setCpuSpeed(double cpuSpeed) { this.cpuSpeed = cpuSpeed; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }

    @Override
    public String toString() {
        return "Product [name=" + name + ", category=" + category + ", ram=" + ram
                + ", cpuSpeed=" + cpuSpeed + ", rating=" + rating + ", os=" + os + "]";
    }
}
