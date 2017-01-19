package ua.nure.piontkovskyi.SummaryTask4.repository;


import ua.nure.piontkovskyi.SummaryTask4.model.User;

public interface UserRepository {

    User getByLogin(String login);

    boolean add(User user);

    boolean remove(int id);

    boolean update(User user);
}
