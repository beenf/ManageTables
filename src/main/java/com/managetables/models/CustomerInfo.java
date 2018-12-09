package com.managetables.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "customer_info", schema = "public", catalog = "customers")
public class CustomerInfo {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;
    private String balance;
    private String birthday;
}
