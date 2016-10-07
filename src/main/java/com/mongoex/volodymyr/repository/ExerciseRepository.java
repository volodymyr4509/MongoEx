package com.mongoex.volodymyr.repository;

import com.mongoex.volodymyr.domain.Exercise;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Exercise entity.
 */
@SuppressWarnings("unused")
public interface ExerciseRepository extends MongoRepository<Exercise,String> {


    Exercise findByNumber(int number);

}
