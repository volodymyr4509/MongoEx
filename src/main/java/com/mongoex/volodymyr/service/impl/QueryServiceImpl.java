package com.mongoex.volodymyr.service.impl;

import com.mongoex.volodymyr.domain.Query;
import com.mongoex.volodymyr.repository.QueryRepo;
import com.mongoex.volodymyr.repository.QueryRepository;
import com.mongoex.volodymyr.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Query.
 */
@Service
public class QueryServiceImpl implements QueryService {

    private final Logger log = LoggerFactory.getLogger(QueryServiceImpl.class);

    @Inject
    private QueryRepository queryRepository;

    @Inject
    private QueryRepo queryRepo;

    /**
     * Save a query.
     *
     * @param query the entity to save
     * @return the persisted entity
     */
    public Query save(Query query) {
        log.debug("Request to save Query : {}", query);
        try {
            String execute = queryRepo.execute(query);
            log.error("Execution result: " + execute);
            query.setResult(execute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Query result = queryRepository.save(query);
        return result;
    }

    /**
     * Get all the queries.
     *
     * @return the list of entities
     */
    public List<Query> findAll() {
        log.debug("Request to get all Queries");
        List<Query> result = queryRepository.findAll();

        return result;
    }

    /**
     * Get one query by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Query findOne(String id) {
        log.debug("Request to get Query : {}", id);
        Query query = queryRepository.findOne(id);
        return query;
    }

    /**
     * Delete the  query by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Query : {}", id);
        queryRepository.delete(id);
    }
}
