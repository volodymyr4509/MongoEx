package com.mongoex.volodymyr.service;

import com.mongoex.volodymyr.domain.Exercise;

import java.util.List;

/**
 * Service Interface for managing Exercise.
 */
public interface ExerciseService {

    /**
     * Save a exercise.
     *
     * @param exercise the entity to save
     * @return the persisted entity
     */
    Exercise save(Exercise exercise);

    /**
     *  Get all the exercises.
     *  
     *  @return the list of entities
     */
    List<Exercise> findAll();

    /**
     *  Get the "id" exercise.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Exercise findOne(String id);

    /**
     *  Delete the "id" exercise.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
