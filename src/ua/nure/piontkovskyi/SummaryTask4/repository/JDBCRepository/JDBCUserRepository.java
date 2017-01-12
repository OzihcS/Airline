package ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Repository;
import ua.nure.piontkovskyi.SummaryTask4.db.Query;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.exception.DataAccessException;
import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Role;
import ua.nure.piontkovskyi.SummaryTask4.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCUserRepository extends JDBCAbstractRepository implements UserRepository {

    private static final String GET_BY_LOGIN = "user.get.by.login";
    private static final String GET_ROLES = "user.get.role.by.id";

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
        User user;
        user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
//        user.setRole(getRole(user.getId()));
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

    private Role getRole(int id) {
        String sql = Query.get(GET_ROLES);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Role.valueOf(String.valueOf(rs.getInt("role_id") - 1));
            }
            return null;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

}
