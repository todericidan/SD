package com.utcluj.api.contracts.dtos;


/**
 * Created by todericidan on 3/18/2017.
 */
public class EmployeeDTO {

    private String username;

    private String password;

    private int isAdmin;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String username, String password, int isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
