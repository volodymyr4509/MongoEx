package com.mongoex.volodymyr.service.impl;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.mongoex.volodymyr.domain.Book;
import com.mongoex.volodymyr.domain.Query;
import com.mongoex.volodymyr.repository.BookRepository;
import com.mongoex.volodymyr.repository.UserQueryRepository;
import com.mongoex.volodymyr.service.BookService;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for managing Book.
 */
@Service
public class BookServiceImpl implements BookService {

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Inject
    private BookRepository bookRepository;
    @Inject
    private UserQueryRepository userQueryRepository;

    /**
     * Save a book.
     *
     * @param book the entity to save
     * @return the persisted entity
     */
    public Book save(Book book) {
        log.debug("Request to save Book : {}", book);
        Book result = bookRepository.save(book);
        return result;
    }

    /**
     * Get all the books.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Book> findAll(Pageable pageable) {
        log.debug("Request to get all Books");
        Page<Book> result = bookRepository.findAll(pageable);
        return result;
    }

    /**
     * Get one book by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Book findOne(String id) {
        log.debug("Request to get Book : {}", id);
        Book book = bookRepository.findOne(id);
        return book;
    }

    /**
     * Delete the  book by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Book : {}", id);
        bookRepository.delete(id);
    }

    public List<DBObject> execute(Query q) {
        DBObject query = null;
        DBObject projection = null;
        List<DBObject> results = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray("[" + q.getQueryBody() + "]");
            query = (DBObject) JSON.parse(jsonArray.getString(0));
            if (!jsonArray.isNull(1)){
                projection = (DBObject) JSON.parse(jsonArray.getString(1));
            }
        } catch (JSONException e) {
            log.error("Cannot parse incoming query {}", q.getQueryBody(), e.getMessage());
        }
        if (query != null) {
            results = userQueryRepository.find(query, projection);
        }

        return results;
    }
}
