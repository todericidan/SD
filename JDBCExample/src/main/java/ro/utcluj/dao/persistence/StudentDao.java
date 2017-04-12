package ro.utcluj.dao.persistence;

import org.springframework.stereotype.Repository;
import ro.utcluj.dao.rowmapper.StudentRowMapper;
import ro.utcluj.model.Student;

import java.util.List;

/**
 * Created by Admin on 3/12/2017.
 */
@Repository
public class StudentDao extends Dao{


    public List<Student> getAllStudents() {
        List<Student> listOfStudent = jdbcTemplate.query("select * from student", new StudentRowMapper());
        return listOfStudent;
    }

    public Student getStudentByName(String name){
        return jdbcTemplate.queryForObject("select * from student where name='"+name+"';", new StudentRowMapper());
    }

    public Student getStudentById(int id){
        return jdbcTemplate.queryForObject("select * from student where studentid='"+id+"';", new StudentRowMapper());
    }

    public boolean isNewValidStudent(Student student){
        List<Student> students = getAllStudents();
        for(Student s :students){
            if(s.equals(student)){
                return false;
            }
        }

        return true;
    }

    public boolean isExistentStudentId(int id) {
        List<Student> students = getAllStudents();
        for(Student s :students){
            if(s.getStudentId() == id){
                return true;
            }
        }
        return false;
    }

    public boolean hasNonEmptyFields(Student student){
        if(student.getAddress() == ""){
            return false;
        }
        if(student.getBirthdate() == null){
            return false;
        }
        if(student.getName() == ""){
            return false;
        }

        return true;

    }

    public boolean addStudent(Student student) {
        if(hasNonEmptyFields(student)) {
            if (isNewValidStudent(student)) {
                return jdbcTemplate.update(
                        "INSERT INTO student (name, birthdate, address) VALUES (?, ?, ?)"
                        , student.getName(),
                        student.getBirthdate(),
                        student.getAddress()) == 1;

            }
        }
        return false;
    }

    public boolean updateStudent(Student student){
        if(hasNonEmptyFields(student)){
            if(isExistentStudentId(student.getStudentId())){
                jdbcTemplate.update(
                        "UPDATE student SET name='" + student.getName() +
                                "', birthdate='" + student.getBirthdate()
                                + "', address='" + student.getAddress() +
                                "' WHERE studentid=" + student.getStudentId() + ";");
                return true;
            }
        }

        return false;
    }

    public boolean deleteStudent(int id) {

        if(isExistentStudentId(id)){
            jdbcTemplate.update("delete from enrollement where studentid="+id+";");
            jdbcTemplate.update("delete FROM student where studentid ="+id+" ;");
            return true;
        }

        return false;
    }



}
