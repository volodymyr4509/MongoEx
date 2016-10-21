package com.mongoex.volodymyr.repository;

import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by vkret on 21.10.16.
 */
public interface UserQueryRepository {
    List<DBObject> find(DBObject query, DBObject projection);
}
