package com.szabicycle.szabicycle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Component {

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

    private String imgUri;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> imgUris = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TypeOfComponent typeOfComponent;
}
