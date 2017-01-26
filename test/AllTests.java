import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository.JDBCFlightRepositoryTest;
import ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository.JDBCRequestRepositoryTest;
import ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository.JDBCUserRepositoryTest;
import ua.nure.piontkovskyi.SummaryTask4.validator.FlightValidatorTest;
import ua.nure.piontkovskyi.SummaryTask4.validator.UserValidatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({JDBCUserRepositoryTest.class, JDBCFlightRepositoryTest.class,
        JDBCRequestRepositoryTest.class, UserValidatorTest.class, FlightValidatorTest.class})
public class AllTests {
}
