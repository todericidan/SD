package com.utcluj.api.contracts.dtos;

import java.sql.Timestamp;

/**
 * Created by todericidan on 3/19/2017.
 */

public class LogDTO {

    private String logTime;

    private String username;

    private String logDescription;

    public LogDTO() {
    }

    public LogDTO(String logTime, String username, String logDescription) {
        this.logTime = logTime;
        this.username = username;
        this.logDescription = logDescription;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }
}
