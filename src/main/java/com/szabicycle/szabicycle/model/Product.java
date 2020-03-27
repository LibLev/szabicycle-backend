package com.szabicycle.szabicycle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private String details;
    @NotNull
    private int price;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> imgUris = new ArrayList<>();
}
