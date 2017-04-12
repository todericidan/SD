package com.utcluj.persistence.model.primarykey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by todericidan on 3/19/2017.
 */
@Embeddable
public class LogPrimaryKey implements Serializable {

    @Column(name="log_time")
    private Timestamp logTime;

    @Column(name ="username")
    private String username;

    public LogPrimaryKey() {
    }

    public LogPrimaryKey(Timestamp logTime, String username) {
        this.logTime = logTime;
        this.username = username;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LogPrimaryKey{");
        sb.append("logTime=").append(logTime);
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
