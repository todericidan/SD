package ro.utcluj.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ro.utcluj.model.Enrollement;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 3/12/2017.
 */
public class EnrollementRowMapper implements RowMapper<Enrollement>{

    public Enrollement mapRow(ResultSet resultSet, int i) throws SQLException {
        Enrollement enrollement = new Enrollement();

        enrollement.setCourseId(resultSet.getInt(1));
        enrollement.setStudentId(resultSet.getInt(2));

        return enrollement;
    }
}
