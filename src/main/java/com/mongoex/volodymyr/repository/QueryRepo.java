package com.mongoex.volodymyr.repository;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongoex.volodymyr.domain.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.stereotype.Repository;

/**
 * Created by vkret on 17.10.16.
 */
@Repository
public class QueryRepo {

    @Autowired
    private Mongo mongo;

    @Autowired
    private MongoProperties mongoProperties;

    public String execute(Query query) {
        DB db = mongo.getDB(mongoProperties.getDatabase());
        Object eval = db.eval(query.getQueryBody());


        return eval.toString();
    }
}
