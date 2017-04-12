package ro.utcluj.dao.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Admin on 3/12/2017.
 */
@Repository
public abstract class Dao {

    protected JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
