package com.aceras.dashboard.model;

import com.aceras.dashboard.model.audit.UserDateAudit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "products")
public class Product extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private String status;
    private String type;

    public Product() {
    }

    public Product(String code, String name, String description, String status, String type){
        this.setCreatedBy(1L);
        this.setCreatedAt(new Date().toInstant());
        this.code = code;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type = type;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
