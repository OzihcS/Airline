package ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository;

import junit.framework.TestCase;
import org.junit.Test;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.manager.HikariCPManager;
import ua.nure.piontkovskyi.SummaryTask4.exception.DataAccessException;
import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Status;
import ua.nure.piontkovskyi.SummaryTask4.repository.FlightRepository;

import java.util.Date;
import java.util.List;


public class JDBCFlightRepositoryTest extends TestCase {

    private static final ConnectionHolder holder = new ThreadLocalConnectionHolder();
    private static final Flight flight;

    static {
        holder.set(new HikariCPManager().getConnection());
    }

    private static final FlightRepository rep = new JDBCFlightRepository(holder);

    static {
        flight = new Flight();
        flight.setName("Berlin-London");
        flight.setArriveLocation("London");
        flight.setDepartureLocation("Berlin");
        flight.setDepartureDate(new Date());
        flight.setArriveDate(new Date());
        flight.setStatus(Status.IN_PROGRESS);
    }

    @Test
    public void testAdd() throws Exception {
        assertTrue(rep.add(flight));
    }


    @Test
    public void testGetAll() {
        List<Flight> flightList = rep.getAll();
        assertFalse(flightList.isEmpty());
        for (Flight flight : flightList) {
            if (flight.getName().equals(this.flight.getName())) {
                this.flight.setId(flight.getId());
            }
        }
    }

    @Test
    public void testUpdate() {
        Flight after = new Flight();
        after.setName("another");
        after.setArriveLocation("London");
        after.setDepartureLocation("Berlin");
        after.setDepartureDate(new Date());
        after.setArriveDate(new Date());
        after.setStatus(Status.IN_PROGRESS);
        after.setId(flight.getId());
        rep.update(after);
        assertNotSame(flight.getName(), after.getName());
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateExc() {
        Flight flight = new Flight();
        flight.setDepartureDate(new Date());
        flight.setArriveDate(new Date());
        flight.setStatus(Status.IN_PROGRESS);
        rep.update(flight);
    }


    @Test
    public void testRemove() {
        List<Flight> before = rep.getAll();
        assertTrue(rep.remove(flight.getId()));
        assertNotSame(before.size(), rep.getAll().size());
    }
}