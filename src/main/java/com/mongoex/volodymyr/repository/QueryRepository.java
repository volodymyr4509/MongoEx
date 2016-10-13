package com.mongoex.volodymyr.repository;

import com.mongoex.volodymyr.domain.Query;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Query entity.
 */
@SuppressWarnings("unused")
public interface QueryRepository extends MongoRepository<Query,String> {

}
