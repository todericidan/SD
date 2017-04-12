package junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.utcluj.dao.persistence.CourseDao;
import ro.utcluj.model.Course;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Admin on 3/12/2017.
 */
public class CourseDaoTest {

    private Course course;
    private CourseDao courseDao;

    public CourseDaoTest() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        courseDao = applicationContext.getBean(CourseDao.class);

        course = new Course();
        course.setName("Geometrie");
        course.setTeacher("Ivan Mircea");
        course.setStudyYear(1);
    }

    @Test
    public void insertNewCourseTest() throws Exception {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');//a-z
        course.setName(course.getName()+c);
        assertEquals("Insert New Course Check",true,courseDao.addCourse(course));
    }

    @Test
    public void updateCourseTest() throws Exception {
        List<Course> courses = courseDao.getAllCourses();
        course = courses.get(courses.size()-1);
        course.setTeacher("Ivan Marcel");
        course.setStudyYear(2);
        assertEquals("Update Course Check",true,courseDao.updateCourse(course));
    }

    @Test
    public void deleteCourseTest() throws Exception {
        List<Course> courses = courseDao.getAllCourses();
        course = courses.get(courses.size()-1);
        System.out.println(course);
        assertEquals("Delete Course Check",true,courseDao.deleteCourse(course.getCourseId()));
    }

    @After
    public void tearDown() throws Exception {
        course = null;
        courseDao = null;
        assertNull(course);
        assertNull(courseDao);
    }
}
