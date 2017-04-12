package ro.utcluj.dao.rowmapper;


import org.springframework.jdbc.core.RowMapper;
import ro.utcluj.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 3/12/2017.
 */
public class StudentRowMapper  implements RowMapper<Student>{

    public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Student student = new Student();

        student.setStudentId(resultSet.getInt(1));
        student.setName(resultSet.getString(2));
        student.setBirthdate(resultSet.getDate(3));
        student.setAddress(resultSet.getString(4));

        return student;
    }
}
