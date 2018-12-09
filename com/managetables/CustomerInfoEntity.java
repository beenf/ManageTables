package com.managetables;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer_info", schema = "public", catalog = "customers")
public class CustomerInfoEntity {
}
