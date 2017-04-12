package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.utcluj.dao.persistence.StudentDao;
import ro.utcluj.model.Student;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 3/12/2017.
 */

public class StudentDaoTest {

    private Student student;
    private StudentDao studentDao;

    public StudentDaoTest() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        studentDao = applicationContext.getBean(StudentDao.class);

        student = new Student();
        student.setName("George Marin");
        student.setBirthdate(new Date(1970,01,01));
        student.setAddress("Ciresilor nr 22");

    }

    @Test
    public void insertNewStudentTest() throws Exception{
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');//a-z
        student.setName(student.getName()+c);
        assertEquals("Insert New Student Check",true,studentDao.addStudent(student));

    }

    @Test
    public void updateStudentTest() throws Exception{
        List<Student> students = studentDao.getAllStudents();
        student = students.get(students.size()-1);
        student.setAddress("Calusarilor nr 30");
        assertEquals("Update Student Check",true,studentDao.updateStudent(student));
    }

    @Test
    public void deleteStudentTest() throws Exception{
        List<Student> students = studentDao.getAllStudents();
        student = students.get(students.size()-1);
        System.out.println(student);
        assertEquals("Delete Student Check",true,studentDao.deleteStudent(student.getStudentId()));
    }

    @After
    public void tearDown() throws Exception{
        studentDao = null;
        student = null;
        assertNull(student);
        assertNull(studentDao);

    }





}
