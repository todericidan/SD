package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Admin on 3/14/2017.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentDaoTest.class,
        CourseDaoTest.class,
        EnrollementDaoTest.class
})
public class UniTestSuite {
}
