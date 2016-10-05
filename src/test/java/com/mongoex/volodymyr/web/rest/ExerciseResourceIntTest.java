package com.mongoex.volodymyr.web.rest;

import com.mongoex.volodymyr.MongoExApp;

import com.mongoex.volodymyr.domain.Exercise;
import com.mongoex.volodymyr.repository.ExerciseRepository;
import com.mongoex.volodymyr.service.ExerciseService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExerciseResource REST controller.
 *
 * @see ExerciseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoExApp.class)
public class ExerciseResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));


    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_ANSWER = "AAAAA";
    private static final String UPDATED_ANSWER = "BBBBB";
    private static final String DEFAULT_HINT = "AAAAA";
    private static final String UPDATED_HINT = "BBBBB";

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private ExerciseRepository exerciseRepository;

    @Inject
    private ExerciseService exerciseService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restExerciseMockMvc;

    private Exercise exercise;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExerciseResource exerciseResource = new ExerciseResource();
        ReflectionTestUtils.setField(exerciseResource, "exerciseService", exerciseService);
        this.restExerciseMockMvc = MockMvcBuilders.standaloneSetup(exerciseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Exercise createEntity() {
        Exercise exercise = new Exercise()
                .number(DEFAULT_NUMBER)
                .description(DEFAULT_DESCRIPTION)
                .answer(DEFAULT_ANSWER)
                .hint(DEFAULT_HINT)
                .last_modified_date(DEFAULT_LAST_MODIFIED_DATE);
        return exercise;
    }

    @Before
    public void initTest() {
        exerciseRepository.deleteAll();
        exercise = createEntity();
    }

    @Test
    public void createExercise() throws Exception {
        int databaseSizeBeforeCreate = exerciseRepository.findAll().size();

        // Create the Exercise

        restExerciseMockMvc.perform(post("/api/exercises")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isCreated());

        // Validate the Exercise in the database
        List<Exercise> exercises = exerciseRepository.findAll();
        assertThat(exercises).hasSize(databaseSizeBeforeCreate + 1);
        Exercise testExercise = exercises.get(exercises.size() - 1);
        assertThat(testExercise.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testExercise.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testExercise.getAnswer()).isEqualTo(DEFAULT_ANSWER);
        assertThat(testExercise.getHint()).isEqualTo(DEFAULT_HINT);
        assertThat(testExercise.getLast_modified_date()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = exerciseRepository.findAll().size();
        // set the field null
        exercise.setDescription(null);

        // Create the Exercise, which fails.

        restExerciseMockMvc.perform(post("/api/exercises")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isBadRequest());

        List<Exercise> exercises = exerciseRepository.findAll();
        assertThat(exercises).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllExercises() throws Exception {
        // Initialize the database
        exerciseRepository.save(exercise);

        // Get all the exercises
        restExerciseMockMvc.perform(get("/api/exercises?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(exercise.getId())))
                .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER.toString())))
                .andExpect(jsonPath("$.[*].hint").value(hasItem(DEFAULT_HINT.toString())))
                .andExpect(jsonPath("$.[*].last_modified_date").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    public void getExercise() throws Exception {
        // Initialize the database
        exerciseRepository.save(exercise);

        // Get the exercise
        restExerciseMockMvc.perform(get("/api/exercises/{id}", exercise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(exercise.getId()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.answer").value(DEFAULT_ANSWER.toString()))
            .andExpect(jsonPath("$.hint").value(DEFAULT_HINT.toString()))
            .andExpect(jsonPath("$.last_modified_date").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    public void getNonExistingExercise() throws Exception {
        // Get the exercise
        restExerciseMockMvc.perform(get("/api/exercises/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExercise() throws Exception {
        // Initialize the database
        exerciseService.save(exercise);

        int databaseSizeBeforeUpdate = exerciseRepository.findAll().size();

        // Update the exercise
        Exercise updatedExercise = exerciseRepository.findOne(exercise.getId());
        updatedExercise
                .number(UPDATED_NUMBER)
                .description(UPDATED_DESCRIPTION)
                .answer(UPDATED_ANSWER)
                .hint(UPDATED_HINT)
                .last_modified_date(UPDATED_LAST_MODIFIED_DATE);

        restExerciseMockMvc.perform(put("/api/exercises")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedExercise)))
                .andExpect(status().isOk());

        // Validate the Exercise in the database
        List<Exercise> exercises = exerciseRepository.findAll();
        assertThat(exercises).hasSize(databaseSizeBeforeUpdate);
        Exercise testExercise = exercises.get(exercises.size() - 1);
        assertThat(testExercise.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testExercise.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testExercise.getAnswer()).isEqualTo(UPDATED_ANSWER);
        assertThat(testExercise.getHint()).isEqualTo(UPDATED_HINT);
        assertThat(testExercise.getLast_modified_date()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    public void deleteExercise() throws Exception {
        // Initialize the database
        exerciseService.save(exercise);

        int databaseSizeBeforeDelete = exerciseRepository.findAll().size();

        // Get the exercise
        restExerciseMockMvc.perform(delete("/api/exercises/{id}", exercise.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Exercise> exercises = exerciseRepository.findAll();
        assertThat(exercises).hasSize(databaseSizeBeforeDelete - 1);
    }
}
