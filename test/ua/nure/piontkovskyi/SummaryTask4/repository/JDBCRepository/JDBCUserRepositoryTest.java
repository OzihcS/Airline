package ua.nure.piontkovskyi.SummaryTask4.repository.JDBCRepository;

import org.junit.Test;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.manager.HikariCPManager;
import ua.nure.piontkovskyi.SummaryTask4.exception.DataAccessException;
import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Role;
import ua.nure.piontkovskyi.SummaryTask4.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class JDBCUserRepositoryTest {

    private static final ConnectionHolder holder = new ThreadLocalConnectionHolder();
    private static final User user;

    static {
        holder.set(new HikariCPManager().getConnection());
    }

    private static final UserRepository rep = new JDBCUserRepository(holder);

    static {
        user = new User();
        user.setName("Name");
        user.setLogin("login");
        user.setPassword("password");
        user.setRole(Role.DISPATCHER);
    }

    @Test
    public void addTest() {
        assertTrue(rep.add(user));
        User user2 = rep.getByLogin(user.getLogin());
        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getPassword(), user2.getPassword());
        assertEquals(user.getLogin(), user2.getLogin());
        rep.remove(user2.getId());
    }

    @Test(expected = DataAccessException.class)
    public void saveFailTest() {
        User user1 = new User();
        user1.setLogin(user.getLogin());
        user1.setPassword(user.getPassword());
        user1.setRole(Role.DISPATCHER);
        user1.setName(null);
        assertTrue(rep.add(user1));
        User user2 = rep.getByLogin(user1.getLogin());
        rep.remove(user2.getId());
    }


    @Test
    public void updateTest() {
        User user = rep.getById(1);
        user.setName("name");

        assertTrue(rep.update(user));
        User user2 = rep.getByLogin(user.getLogin());

        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getPassword(), user2.getPassword());
        assertEquals(user.getLogin(), user2.getLogin());
    }


    @Test(expected = DataAccessException.class)
    public void updateFailTest() {
        User user = rep.getById(1);

        user.setLogin(null);
        user.setPassword("password");
        user.setRole(Role.DISPATCHER);

        rep.update(user);

    }
    @Test
    public void deleteTest() {
        rep.add(user);
        User user2 = rep.getByLogin(user.getLogin());
        int before = rep.getAll().size();
        rep.remove(user2.getId());
        assertEquals(before - 1, rep.getAll().size());
    }

    @Test
    public void deleteFailTest() {
        assertEquals(false, rep.remove(-1));
    }

    @Test
    public void getTest() {
        rep.add(user);
        User user1 = rep.getByLogin(user.getLogin());

        User user2 = rep.getById(user1.getId());

        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getLogin(), user2.getLogin());

        rep.remove(user2.getId());
    }

    @Test
    public void getFailTest() {
        User user2 = rep.getById(0);
        assertNull(user2);
    }

}