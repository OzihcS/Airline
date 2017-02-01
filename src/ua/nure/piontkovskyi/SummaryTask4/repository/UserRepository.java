package ua.nure.piontkovskyi.SummaryTask4.repository;


import ua.nure.piontkovskyi.SummaryTask4.entity.User;

import java.util.List;

/**
 * User repository class which contain methods interaction with user table in DB.
 */
public interface UserRepository {

    User getByLogin(String login);

    boolean add(User user);

    boolean remove(int id);

    boolean update(User user);

    List<User> getAll();

    List<User> getAdmins();

    User getById(int i);
}
