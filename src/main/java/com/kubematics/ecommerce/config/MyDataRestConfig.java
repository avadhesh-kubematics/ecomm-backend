package com.kubematics.ecommerce.config;

import com.kubematics.ecommerce.entity.Product;
import com.kubematics.ecommerce.entity.ProductCategory;
import javax.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by am on 2021-01-11
 */

@SuppressWarnings("all")
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
        ExposureConfiguration config = restConfig.getExposureConfiguration();
        config.forDomainType(Product.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE))
        .withCollectionExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE));
        config.forDomainType(ProductCategory.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE))
                .withCollectionExposure((metadata, httpMethods) ->
                        httpMethods.disable(HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE));

        //call an internal helper method
        exposedIds(restConfig);
    }


    private void exposedIds(RepositoryRestConfiguration config) {
        //expose entity ids
        //
        // - get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        // - create an array of the entityh types
        List<Class> entityClasses = new ArrayList<>();

        // - get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // - expose the entity ids for the array of entity/domain types

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }
}