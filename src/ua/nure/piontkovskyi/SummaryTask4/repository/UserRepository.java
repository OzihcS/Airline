package ua.nure.piontkovskyi.SummaryTask4.repository;


import ua.nure.piontkovskyi.SummaryTask4.model.User;

public interface UserRepository {

    User getByLogin(String login);

}
