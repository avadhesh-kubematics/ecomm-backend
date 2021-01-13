package com.kubematics.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by am on 2021-01-13
 */

@Entity
@Table(name="state")
@Getter
@Setter
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    //TODO: mant to one with state



}