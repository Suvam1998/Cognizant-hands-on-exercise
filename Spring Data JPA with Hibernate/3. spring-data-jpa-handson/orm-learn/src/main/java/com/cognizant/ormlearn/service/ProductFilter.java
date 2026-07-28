package com.cognizant.ormlearn.service;

/**
 * Optional search criteria — any field left null is ignored. Mirrors the
 * Amazon-style filter panel (category, review, RAM, CPU speed, OS).
 */
public class ProductFilter {

    private String keyword;      // matches product name
    private String category;
    private Double minRating;    // customer review
    private Integer minRam;
    private Double minCpuSpeed;
    private String os;

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getMinRating() { return minRating; }
    public void setMinRating(Double minRating) { this.minRating = minRating; }
    public Integer getMinRam() { return minRam; }
    public void setMinRam(Integer minRam) { this.minRam = minRam; }
    public Double getMinCpuSpeed() { return minCpuSpeed; }
    public void setMinCpuSpeed(Double minCpuSpeed) { this.minCpuSpeed = minCpuSpeed; }
    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }
}
