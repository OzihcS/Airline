package repository.JDBCRepository;

import annotation.Repository;
import db.Query;
import db.holder.ConnectionHolder;
import entity.AdminStatistic;
import entity.Request;
import entity.enums.RequestStatus;
import exception.DataAccessException;
import repository.JDBCRepository.JDBCAbstractRepository;
import repository.RequestRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of RequestRepository
 */

@Repository
public class JDBCRequestRepository extends JDBCAbstractRepository implements RequestRepository {

    private static final String UPDATE_REQUEST = "request.update";
    private static final String GET_REQUEST = "request.get";
    private static final String ADD_REQUEST = "request.add";
    private static final String REMOVE_REQUEST = "request.remove";
    private static final String GET_STATISTIC = "user.admin.get.statistic";
    private static final String UPDATE_STATISTIC = "user.admin.update.statistic";


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
        request.setFrom(rs.getString("fromUser"));
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

    public boolean updateStatistic(int id, AdminStatistic statistic) {
        String sql = Query.get(UPDATE_STATISTIC);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, statistic.getReject());
            ps.setInt(2, statistic.getExecute());
            ps.setInt(3, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
        return false;
    }

    public AdminStatistic getStatistic(int id) {
        String sql = Query.get(GET_STATISTIC);
        AdminStatistic statistic;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            statistic = new AdminStatistic();
            while (resultSet.next()) {
                statistic.setReject(resultSet.getInt("reject"));
                statistic.setExecute(resultSet.getInt("execute"));
            }
            return statistic;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public boolean add(Request request) {
        String sql = Query.get(ADD_REQUEST);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setString(k++, request.getFrom());
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

    @Override
    public boolean remove(int id) {
        return delete(id, Query.get(REMOVE_REQUEST));
    }
}
