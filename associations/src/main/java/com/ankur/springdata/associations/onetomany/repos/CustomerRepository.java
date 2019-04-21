package com.ankur.springdata.associations.onetomany.repos;

import org.springframework.data.repository.CrudRepository;

import com.ankur.springdata.associations.onetomany.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
