package ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Repository;
import ua.nure.piontkovskyi.SummaryTask4.db.Query;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.exception.DataAccessException;
import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Status;
import ua.nure.piontkovskyi.SummaryTask4.repository.FlightRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCFlightRepository extends JDBCAbstractRepository implements FlightRepository {

    private static final String GET_FLIGHT_BY_ID = "flight.get.by.id";
    private static final String GET_ALL_FLIGHT = "flight.get.all";
    private static final String ADD_FLIGHT = "flight.add";
    private static final String UPDATE_FLIGHT = "flight.update";
    private static final String REMOVE_FLIGHT = "flight.delete";

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public JDBCFlightRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    protected Flight extractFromResultSet(ResultSet rs) throws SQLException {
        Flight flight = new Flight();
        flight.setId(rs.getInt("id"));
        flight.setName(rs.getString("name"));
        flight.setDepartureLocation(rs.getString("departure_location"));
        flight.setArriveLocation(rs.getString("arrive_location"));
        flight.setStatus(Status.getStatus(rs.getString("status")));
        flight.setDepartureDate(rs.getDate("departure_date"));
        flight.setArriveDate(rs.getDate("arrive_date"));
        //TODO flight.setBrigade();
        return flight;
    }

    @Override
    public List<Flight> getAll() {
        String sql = Query.get(GET_ALL_FLIGHT);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            List<Flight> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(extractFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public Flight getById(int id) {
        return (Flight) get(id, Query.get(GET_FLIGHT_BY_ID));
    }

    @Override
    public boolean add(Flight flight) {
        String sql = Query.get(ADD_FLIGHT);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setInt(k++, flight.getId());
            ps.setString(k++, flight.getName());
            ps.setString(k++, flight.getDepartureLocation());
            ps.setString(k++, flight.getArriveLocation());
            ps.setString(k++, flight.getStatus());
            ps.setDate(k++, flight.getDepartureDate());
            ps.setDate(k++, flight.getArriveDate());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        return delete(id, Query.get(REMOVE_FLIGHT));
    }

    @Override
    public boolean update(Flight flight) {
        String sql = Query.get(UPDATE_FLIGHT);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setString(k++, flight.getName());
            ps.setString(k++, flight.getDepartureLocation());
            ps.setString(k++, flight.getArriveLocation());
            ps.setString(k++, flight.getStatus());
            ps.setDate(k++, flight.getArriveDate());
            ps.setDate(k++, flight.getArriveDate());
            ps.setInt(k++, flight.getId());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
        }
        return false;
    }
}
