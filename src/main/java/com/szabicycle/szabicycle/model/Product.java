package com.szabicycle.szabicycle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String brand;
    private String details;
    private int price;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> imgUris = new ArrayList<>();
}
