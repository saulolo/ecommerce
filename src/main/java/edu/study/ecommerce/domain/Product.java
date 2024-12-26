package edu.study.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private Integer id;
    private String code;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    private User user;

    public Product() {
        this.setCode(UUID.randomUUID().toString());
    }
}

