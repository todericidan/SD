package ro.utcluj.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.utcluj.dao.persistence.CourseDao;
import ro.utcluj.dao.persistence.EnrollementDao;
import ro.utcluj.dao.persistence.StudentDao;
import ro.utcluj.model.Course;
import ro.utcluj.model.Enrollement;
import ro.utcluj.model.Student;

import java.util.List;

/**
 * Created by Admin on 3/14/2017.
 */
public class MockFunctionality {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        StudentDao studentDao = applicationContext.getBean(StudentDao.class);
        System.out.println("Students Calin Timbus info:");
        System.out.println(studentDao.getStudentByName("Calin Timbus"));

        CourseDao courseDao= applicationContext.getBean(CourseDao.class);
        Course course = courseDao.getCourseByName("Geometrie");
        System.out.println("Info about course "+course.getName());
        System.out.println(course);


        System.out.println("Students enrolled  ");
        EnrollementDao enrollementDao = applicationContext.getBean(EnrollementDao.class);
       // System.out.println(enrollementDao.getEnrollementsForGiveCourse(course.getCourseId()));
        List<Enrollement> enrollements = enrollementDao.getEnrollementsForGiveCourse(course.getCourseId());
        for(Enrollement e:enrollements){
            System.out.println(studentDao.getStudentById(e.getStudentId()));
        }



    }

}
