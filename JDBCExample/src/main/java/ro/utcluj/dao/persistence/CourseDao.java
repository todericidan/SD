package ro.utcluj.dao.persistence;

import org.springframework.stereotype.Repository;
import ro.utcluj.dao.rowmapper.CourseRowMapper;
import ro.utcluj.model.Course;

import java.util.List;

/**
 * Created by Admin on 3/12/2017.
 */
@Repository
public class CourseDao extends Dao{

    public List<Course> getAllCourses(){
        return jdbcTemplate.query("select * from course", new CourseRowMapper());
    }

    public Course getCourseByName(String name){
        return jdbcTemplate.queryForObject("select * from course where name='"+name+"';", new CourseRowMapper());
    }

    public boolean isExistentCourseId(int id) {
        List<Course> courses = getAllCourses();
        for(Course c :courses){
            if(c.getCourseId() == id){
                return true;
            }
        }
        return false;
    }

    public boolean isNewValidCourse(Course course){
        List<Course> courses = getAllCourses();
        for(Course c :courses){
            if(c.equals(course)){
                return false;
            }
        }
        return true;
    }

    private boolean hasNonEmptyFields(Course course){
        if(course.getName() == ""){
            return false;
        }
        if(course.getStudyYear() == 0){
            return false;
        }
        if(course.getTeacher() == ""){
            return false;
        }
        return true;
    }

    public boolean addCourse(Course course){
        if (hasNonEmptyFields(course)) {
            if(isNewValidCourse(course)) {
                return jdbcTemplate.update(
                        "INSERT INTO course (name, teacher, studyyear) VALUES (?, ?, ?)"
                        , course.getName(),
                        course.getTeacher(),
                        course.getStudyYear()) == 1;
            }
        }
        return false;
    }

    public boolean updateCourse(Course course){
        if(isExistentCourseId(course.getCourseId())){
            if(hasNonEmptyFields(course)){
                return jdbcTemplate.update(
                        "UPDATE course SET name='" + course.getName() +
                                "', teacher='" + course.getTeacher() +
                                "', studyyear='" + course.getStudyYear() +
                                "' WHERE courseid=" + course.getCourseId() + ";")==1;

            }
        }
        return false;
    }

    public boolean deleteCourse(int id) {
        if(isExistentCourseId(id)){
            jdbcTemplate.update("delete from enrollement where courseid="+id+";");
            jdbcTemplate.update("delete FROM course where courseid ="+id+" ;");
            return true;
        }
        return false;
    }

}
