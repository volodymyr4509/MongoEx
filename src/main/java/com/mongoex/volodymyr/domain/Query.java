package com.mongoex.volodymyr.domain;

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
    private String exerciseNumber;

    @Field("query_body")
    private String queryBody;

    @Field("execution_time")
    private ZonedDateTime executionTime;

    @Field("result")
    private String result;

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

    public String getExerciseNumber() {
        return exerciseNumber;
    }

    public Query exerciseId(String exerciseId) {
        this.exerciseNumber = exerciseId;
        return this;
    }

    public void setExerciseNumber(String exerciseNumber) {
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

    public ZonedDateTime getExecutionTime() {
        return executionTime;
    }

    public Query executionTime(ZonedDateTime executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    public void setExecutionTime(ZonedDateTime executionTime) {
        this.executionTime = executionTime;
    }

    public String getResult() {
        return result;
    }

    public Query result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
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
        if(query.id == null || id == null) {
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
            "id=" + id +
            ", userId='" + userId + "'" +
            ", exerciseNumber='" + exerciseNumber + "'" +
            ", queryBody='" + queryBody + "'" +
            ", executionTime='" + executionTime + "'" +
            ", result='" + result + "'" +
            '}';
    }
}
