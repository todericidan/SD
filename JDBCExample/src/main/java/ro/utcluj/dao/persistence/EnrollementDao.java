package ro.utcluj.dao.persistence;

import org.springframework.stereotype.Repository;
import ro.utcluj.dao.rowmapper.EnrollementRowMapper;
import ro.utcluj.model.Enrollement;

import java.util.List;

/**
 * Created by Admin on 3/12/2017.
 */
@Repository
public class EnrollementDao extends Dao {

    public List<Enrollement> getAllEnrollements(){
        return jdbcTemplate.query("select * from enrollement", new EnrollementRowMapper());
    }

    private boolean isNewValidEnrollement(Enrollement enrollement){
        List<Enrollement> enrollements = getAllEnrollements();
        for(Enrollement e :enrollements){
            if(e.equals(enrollement)){
                return false;
            }
        }
        return true;
    }

    public boolean hasNonEmptyFields(Enrollement enrollement){
        if(enrollement.getCourseId()==0){
            return false;
        }
        if(enrollement.getStudentId()==0){
            return false;
        }
        return true;
    }

    public boolean enrollStudentToCourse(Enrollement enrollement){
        if(hasNonEmptyFields(enrollement)) {
            if (isNewValidEnrollement(enrollement)) {
                return jdbcTemplate.update(
                        "INSERT INTO enrollement (studentid, courseid) VALUES (?, ?)"
                        , enrollement.getStudentId(), enrollement.getCourseId()) == 1;

            }
        }
        return false;
    }

    public boolean deleteEnrollement(Enrollement enrollement){
        if(hasNonEmptyFields(enrollement)) {
            if (!isNewValidEnrollement(enrollement)) {
                jdbcTemplate.update("delete from enrollement where studentid=" + enrollement.getStudentId() +
                        " and courseid=" + enrollement.getCourseId() + " ;");
                return true;
            }
        }
        return false;
    }


    public List<Enrollement> getEnrollementsForGiveCourse(int id) {
        return jdbcTemplate.query("select * from enrollement where courseid="+id, new EnrollementRowMapper());
    }

}
