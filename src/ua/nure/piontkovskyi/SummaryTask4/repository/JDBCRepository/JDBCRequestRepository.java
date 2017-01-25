package ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Repository;
import ua.nure.piontkovskyi.SummaryTask4.db.Query;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.exception.DataAccessException;
import ua.nure.piontkovskyi.SummaryTask4.model.Request;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.RequestStatus;
import ua.nure.piontkovskyi.SummaryTask4.repository.RequestRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCRequestRepository extends JDBCAbstractRepository implements RequestRepository {

    private static final String UPDATE_REQUEST = "request.update";
    private static final String GET_REQUEST = "request.get";
    private static final String ADD_REQUEST = "request.add";


    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public JDBCRequestRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    protected Request extractFromResultSet(ResultSet rs) throws SQLException {
        Request request = new Request();
        request.setId(rs.getInt("id"));
        request.setFromId(rs.getInt("from_id"));
        request.setToId(rs.getInt("to_id"));
        request.setTitle(rs.getString("title"));
        request.setMessage(rs.getString("message"));
        request.setStatus(RequestStatus.values()[rs.getInt("status")]);
        request.setDate(rs.getDate("date"));
        return request;
    }

    @Override
    public List<Request> get(int id) {
        String sql = Query.get(GET_REQUEST);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            List<Request> list = new ArrayList<>();
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
    public boolean update(Request request) {
        String sql = Query.get(UPDATE_REQUEST);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setInt(k++, RequestStatus.index((request.getStatus())));
            ps.setInt(k++, request.getId());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
        return false;
    }

    @Override
    public boolean add(Request request) {
        String sql = Query.get(ADD_REQUEST);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setInt(k++, request.getFromId());
            ps.setInt(k++, request.getToId());
            ps.setString(k++, request.getTitle());
            ps.setString(k++, request.getMessage());
            ps.setInt(k++, RequestStatus.index((request.getStatus())));
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
        return false;
    }
}
