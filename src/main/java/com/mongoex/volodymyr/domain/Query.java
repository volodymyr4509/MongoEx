package com.mongoex.volodymyr.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Query.
 */
@Document(collection = "query")
public class Query implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("user_id")
    private String userId;

    @Field("exercise_number")
    private int exerciseNumber;

    @Field("query_body")
    private String queryBody;

    @CreatedDate
    @Field("created_date")
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Field("result")
    private Object result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public Query userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getExerciseNumber() {
        return exerciseNumber;
    }

    public Query exerciseId(int exerciseId) {
        this.exerciseNumber = exerciseId;
        return this;
    }

    public void setExerciseNumber(int exerciseNumber) {
        this.exerciseNumber = exerciseNumber;
    }

    public String getQueryBody() {
        return queryBody;
    }

    public Query queryBody(String queryBody) {
        this.queryBody = queryBody;
        return this;
    }

    public void setQueryBody(String queryBody) {
        this.queryBody = queryBody;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public Query createdDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Object getResult() {
        return result;
    }

    public Query result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Query query = (Query) o;
        if (query.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, query.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", exerciseNumber='" + exerciseNumber + '\'' +
                ", queryBody='" + queryBody + '\'' +
                ", createdDate=" + createdDate +
                ", result=" + result +
                '}';
    }
}
