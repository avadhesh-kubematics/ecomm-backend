package com.kubematics.ecommerce.dao;

import com.kubematics.ecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by am on 2021-01-13
 */

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "counteries", path="counteries")
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
