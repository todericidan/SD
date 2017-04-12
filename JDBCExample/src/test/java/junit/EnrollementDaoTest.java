package junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.utcluj.dao.persistence.EnrollementDao;
import ro.utcluj.model.Enrollement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Created by Admin on 3/13/2017.
 */
public class EnrollementDaoTest {

    private Enrollement enrollement;
    private EnrollementDao enrollementDao;

    public EnrollementDaoTest() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        enrollementDao = applicationContext.getBean(EnrollementDao.class);

        enrollement = new Enrollement();
        enrollement.setStudentId(1);
        enrollement.setCourseId(2);
    }

    @Test
    public void insertNewEnrollementTest() throws Exception {

        assertEquals("Insert New Enrollement Check",true,enrollementDao.enrollStudentToCourse(enrollement));

    }

    @Test
    public void deleteEnrollementTest() throws Exception {

        System.out.println(enrollement);
        assertEquals("Delete Enrollement Check",true,enrollementDao.deleteEnrollement(enrollement));
    }

    @After
    public void tearDown() throws Exception{
        enrollementDao = null;
        enrollement = null;
        assertNull(enrollement);
        assertNull(enrollementDao);
    }


}
