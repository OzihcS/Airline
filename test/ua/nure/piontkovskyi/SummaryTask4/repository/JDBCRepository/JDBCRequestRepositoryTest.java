package ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository;

import junit.framework.TestCase;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.manager.HikariCPManager;
import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.model.Request;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.RequestStatus;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Status;
import ua.nure.piontkovskyi.SummaryTask4.repository.FlightRepository;
import ua.nure.piontkovskyi.SummaryTask4.repository.RequestRepository;

import java.util.Date;
import java.util.List;


public class JDBCRequestRepositoryTest extends TestCase {

    private static final ConnectionHolder holder = new ThreadLocalConnectionHolder();
    private static final Request request;

    static {
        holder.set(new HikariCPManager().getConnection());
    }

    private static final RequestRepository rep = new JDBCRequestRepository(holder);

    static {
        request = new Request();
        request.setTitle("Test title");
        request.setToId(1);
        request.setMessage("Test message");
        request.setStatus(RequestStatus.UNCONFIRMED);
        request.setDate(new Date());
        request.setFrom("Test user");
    }


    public void testGet() throws Exception {
        assertNotNull(rep.get(request.getToId()));
    }

    public void testUpdate() throws Exception {
        Request another = request;
        another.setStatus(RequestStatus.EXECUTED);
        assertTrue(rep.update(request));
    }

    public void testAdd() throws Exception {
        assertTrue(rep.add(request));
        request.setId(3);
    }

}