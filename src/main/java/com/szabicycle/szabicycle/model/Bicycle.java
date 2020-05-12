package com.szabicycle.szabicycle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bicycle {

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
    private String frame;
    @NotNull
    private String fork;
    @NotNull
    private String groupSet;
    @NotNull
    private String shifters;
    @NotNull
    private String breaks;
    @NotNull
    private String callipers;
    @NotNull
    private String wheels;
    @NotNull
    private String saddle;
    @NotNull
    private String seatPost;
    @NotNull
    private String stem;
    @NotNull
    private String handlebar;
    @NotNull
    private String barTape;
    @NotNull
    private String pedal;
    @NotNull
    private int price;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> imgUris = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private TypeOfBicycle typeOfBicycle;
}
