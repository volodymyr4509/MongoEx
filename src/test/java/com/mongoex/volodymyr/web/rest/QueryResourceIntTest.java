package com.mongoex.volodymyr.web.rest;

import com.mongoex.volodymyr.MongoExApp;

import com.mongoex.volodymyr.domain.Query;
import com.mongoex.volodymyr.repository.QueryRepository;
import com.mongoex.volodymyr.service.QueryService;

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
 * Test class for the QueryResource REST controller.
 *
 * @see QueryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoExApp.class)
public class QueryResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    private static final String DEFAULT_USER_ID = "AAAAA";
    private static final String UPDATED_USER_ID = "BBBBB";
    private static final String DEFAULT_EXERCISE_ID = "AAAAA";
    private static final String UPDATED_EXERCISE_ID = "BBBBB";
    private static final String DEFAULT_QUERY_BODY = "AAAAA";
    private static final String UPDATED_QUERY_BODY = "BBBBB";

    private static final ZonedDateTime DEFAULT_EXECUTION_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_EXECUTION_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_EXECUTION_TIME_STR = dateTimeFormatter.format(DEFAULT_EXECUTION_TIME);
    private static final String DEFAULT_RESULT = "AAAAA";
    private static final String UPDATED_RESULT = "BBBBB";

    @Inject
    private QueryRepository queryRepository;

    @Inject
    private QueryService queryService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restQueryMockMvc;

    private Query query;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QueryResource queryResource = new QueryResource();
        ReflectionTestUtils.setField(queryResource, "queryService", queryService);
        this.restQueryMockMvc = MockMvcBuilders.standaloneSetup(queryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Query createEntity() {
        Query query = new Query()
                .userId(DEFAULT_USER_ID)
                .exerciseId(DEFAULT_EXERCISE_ID)
                .queryBody(DEFAULT_QUERY_BODY)
                .executionTime(DEFAULT_EXECUTION_TIME)
                .result(DEFAULT_RESULT);
        return query;
    }

    @Before
    public void initTest() {
        queryRepository.deleteAll();
        query = createEntity();
    }

    @Test
    public void createQuery() throws Exception {
        int databaseSizeBeforeCreate = queryRepository.findAll().size();

        // Create the Query

        restQueryMockMvc.perform(post("/api/queries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(query)))
                .andExpect(status().isCreated());

        // Validate the Query in the database
        List<Query> queries = queryRepository.findAll();
        assertThat(queries).hasSize(databaseSizeBeforeCreate + 1);
        Query testQuery = queries.get(queries.size() - 1);
        assertThat(testQuery.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testQuery.getExerciseId()).isEqualTo(DEFAULT_EXERCISE_ID);
        assertThat(testQuery.getQueryBody()).isEqualTo(DEFAULT_QUERY_BODY);
        assertThat(testQuery.getExecutionTime()).isEqualTo(DEFAULT_EXECUTION_TIME);
        assertThat(testQuery.getResult()).isEqualTo(DEFAULT_RESULT);
    }

    @Test
    public void getAllQueries() throws Exception {
        // Initialize the database
        queryRepository.save(query);

        // Get all the queries
        restQueryMockMvc.perform(get("/api/queries?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(query.getId())))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
                .andExpect(jsonPath("$.[*].exerciseId").value(hasItem(DEFAULT_EXERCISE_ID.toString())))
                .andExpect(jsonPath("$.[*].queryBody").value(hasItem(DEFAULT_QUERY_BODY.toString())))
                .andExpect(jsonPath("$.[*].executionTime").value(hasItem(DEFAULT_EXECUTION_TIME_STR)))
                .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT.toString())));
    }

    @Test
    public void getQuery() throws Exception {
        // Initialize the database
        queryRepository.save(query);

        // Get the query
        restQueryMockMvc.perform(get("/api/queries/{id}", query.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(query.getId()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.exerciseId").value(DEFAULT_EXERCISE_ID.toString()))
            .andExpect(jsonPath("$.queryBody").value(DEFAULT_QUERY_BODY.toString()))
            .andExpect(jsonPath("$.executionTime").value(DEFAULT_EXECUTION_TIME_STR))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT.toString()));
    }

    @Test
    public void getNonExistingQuery() throws Exception {
        // Get the query
        restQueryMockMvc.perform(get("/api/queries/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateQuery() throws Exception {
        // Initialize the database
        queryService.save(query);

        int databaseSizeBeforeUpdate = queryRepository.findAll().size();

        // Update the query
        Query updatedQuery = queryRepository.findOne(query.getId());
        updatedQuery
                .userId(UPDATED_USER_ID)
                .exerciseId(UPDATED_EXERCISE_ID)
                .queryBody(UPDATED_QUERY_BODY)
                .executionTime(UPDATED_EXECUTION_TIME)
                .result(UPDATED_RESULT);

        restQueryMockMvc.perform(put("/api/queries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedQuery)))
                .andExpect(status().isOk());

        // Validate the Query in the database
        List<Query> queries = queryRepository.findAll();
        assertThat(queries).hasSize(databaseSizeBeforeUpdate);
        Query testQuery = queries.get(queries.size() - 1);
        assertThat(testQuery.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testQuery.getExerciseId()).isEqualTo(UPDATED_EXERCISE_ID);
        assertThat(testQuery.getQueryBody()).isEqualTo(UPDATED_QUERY_BODY);
        assertThat(testQuery.getExecutionTime()).isEqualTo(UPDATED_EXECUTION_TIME);
        assertThat(testQuery.getResult()).isEqualTo(UPDATED_RESULT);
    }

    @Test
    public void deleteQuery() throws Exception {
        // Initialize the database
        queryService.save(query);

        int databaseSizeBeforeDelete = queryRepository.findAll().size();

        // Get the query
        restQueryMockMvc.perform(delete("/api/queries/{id}", query.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Query> queries = queryRepository.findAll();
        assertThat(queries).hasSize(databaseSizeBeforeDelete - 1);
    }
}
