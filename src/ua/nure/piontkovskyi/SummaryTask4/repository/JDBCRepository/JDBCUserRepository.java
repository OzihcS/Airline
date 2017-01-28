package ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Repository;
import ua.nure.piontkovskyi.SummaryTask4.db.Query;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.exception.DataAccessException;
import ua.nure.piontkovskyi.SummaryTask4.entity.User;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Role;
import ua.nure.piontkovskyi.SummaryTask4.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCUserRepository extends JDBCAbstractRepository implements UserRepository {

    private static final String GET_BY_LOGIN = "user.get.by.login";
    private static final String ADD_USER = "user.add";
    private static final String UPDATE_USER = "user.update";
    private static final String REMOVE_USER = "user.remove";
    private static final String GET_ALL_USERS = "user.get.all";
    private static final String GET_ADMINS = "user.get.all.admins";
    private static final String GET_USER_BY_ID = "user.get.by.id";

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public JDBCUserRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    protected User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Role.values()[resultSet.getInt("role_id") - 1]);
        return user;
    }

    @Override
    public User getByLogin(String login) {
        String sql = Query.get(GET_BY_LOGIN);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = extractFromResultSet(rs);
            }
            return user;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public boolean add(User user) {
        String sql = Query.get(ADD_USER);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setString(k++, user.getName());
            ps.setString(k++, user.getLogin());
            ps.setString(k++, user.getPassword());
            ps.setInt(k++, Role.getRoleId(user.getRole()));
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
        return delete(id, Query.get(REMOVE_USER));
    }

    @Override
    public boolean update(User user) {
        String sql = Query.get(UPDATE_USER);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int k = 1;
            ps.setString(k++, user.getName());
            ps.setString(k++, user.getLogin());
            ps.setString(k++, user.getPassword());
            ps.setInt(k++, Role.getRoleId(user.getRole()));
            ps.setInt(k++, user.getId());
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
    public List<User> getAll() {
        String sql = Query.get(GET_ALL_USERS);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            List<User> list = new ArrayList<>();
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
    public List<User> getAdmins() {
        String sql = Query.get(GET_ADMINS);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            List<User> admins = new ArrayList<>();
            while (resultSet.next()) {
                admins.add(extractFromResultSet(resultSet));
            }
            return admins;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public User getById(int id) {
        return (User) get(id, Query.get(GET_USER_BY_ID));

    }
}
