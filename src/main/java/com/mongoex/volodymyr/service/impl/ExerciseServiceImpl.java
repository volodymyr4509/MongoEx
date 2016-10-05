package com.mongoex.volodymyr.service.impl;

import com.mongoex.volodymyr.service.ExerciseService;
import com.mongoex.volodymyr.domain.Exercise;
import com.mongoex.volodymyr.repository.ExerciseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Exercise.
 */
@Service
public class ExerciseServiceImpl implements ExerciseService{

    private final Logger log = LoggerFactory.getLogger(ExerciseServiceImpl.class);
    
    @Inject
    private ExerciseRepository exerciseRepository;

    /**
     * Save a exercise.
     *
     * @param exercise the entity to save
     * @return the persisted entity
     */
    public Exercise save(Exercise exercise) {
        log.debug("Request to save Exercise : {}", exercise);
        Exercise result = exerciseRepository.save(exercise);
        return result;
    }

    /**
     *  Get all the exercises.
     *  
     *  @return the list of entities
     */
    public List<Exercise> findAll() {
        log.debug("Request to get all Exercises");
        List<Exercise> result = exerciseRepository.findAll();

        return result;
    }

    /**
     *  Get one exercise by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Exercise findOne(String id) {
        log.debug("Request to get Exercise : {}", id);
        Exercise exercise = exerciseRepository.findOne(id);
        return exercise;
    }

    /**
     *  Delete the  exercise by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Exercise : {}", id);
        exerciseRepository.delete(id);
    }
}
