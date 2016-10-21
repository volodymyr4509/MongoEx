package com.mongoex.volodymyr.repository;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vkret on 17.10.16.
 */
@Repository
public class UserQueryRepositoryImpl implements UserQueryRepository {
    private final Logger log = LoggerFactory.getLogger(UserQueryRepositoryImpl.class);

    private static final String COLLECTION_TO_QUERY = "book";

    @Autowired
    private Mongo mongo;

    @Autowired
    private MongoProperties mongoProperties;

    @Override
    public List<DBObject> find(DBObject query, DBObject projection) {
        DB db = mongo.getDB(mongoProperties.getDatabase());
//        Object eval = db.eval(query.getQueryBody());

        log.debug("Query database find with query {} and projection {}", query, projection);

        List<DBObject> list = new ArrayList<>();

        db.getCollection(COLLECTION_TO_QUERY).find(query, projection).forEach(list::add);

        return list;
    }

}
