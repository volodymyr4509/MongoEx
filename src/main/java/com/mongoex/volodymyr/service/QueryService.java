package com.mongoex.volodymyr.service;

import com.mongoex.volodymyr.domain.Query;

import java.util.List;

/**
 * Service Interface for managing Query.
 */
public interface QueryService {

    /**
     * Save a query.
     *
     * @param query the entity to save
     * @return the persisted entity
     */
    Query save(Query query);

    /**
     *  Get all the queries.
     *  
     *  @return the list of entities
     */
    List<Query> findAll();

    /**
     *  Get the "id" query.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Query findOne(String id);

    /**
     *  Delete the "id" query.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
