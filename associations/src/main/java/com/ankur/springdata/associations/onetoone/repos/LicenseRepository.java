package com.ankur.springdata.associations.onetoone.repos;

import org.springframework.data.repository.CrudRepository;

import com.ankur.springdata.associations.onetoone.entities.License;

public interface LicenseRepository extends CrudRepository<License, Integer> {

}
