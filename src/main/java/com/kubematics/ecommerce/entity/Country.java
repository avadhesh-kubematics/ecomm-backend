package com.kubematics.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by am on 2021-01-13
 */

@Entity
@Table(name="country")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    //TODO: mant to one with state
    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<State> states;




}
