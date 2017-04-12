package com.utcluj.persistence.model;

import com.utcluj.persistence.model.primarykey.LogPrimaryKey;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by todericidan on 3/19/2017.
 */
@Entity
@Table(name = "loggs")
public class Log {

    @EmbeddedId
    private LogPrimaryKey id;

    @Column(name = "log_description",nullable = false)
    private String logDescription;

    public Log() {
    }

    public Log(LogPrimaryKey id, String logDescription) {
        this.id = id;
        this.logDescription = logDescription;
    }

    public LogPrimaryKey getId() {
        return id;
    }

    public void setId(LogPrimaryKey id) {
        this.id = id;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }




    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Log{");
        sb.append("id=").append(id);
        sb.append(", logDescription='").append(logDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
