package ro.utcluj.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ro.utcluj.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 3/12/2017.
 */
public class CourseRowMapper implements RowMapper<Course>{

    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course course = new Course();

        course.setCourseId(resultSet.getInt(1));
        course.setName(resultSet.getString(2));
        course.setTeacher(resultSet.getString(3));
        course.setStudyYear(resultSet.getInt(4));

        return course;
    }
}
