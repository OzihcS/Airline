package ua.nure.piontkovskyi.SummaryTask4.service;


import ua.nure.piontkovskyi.SummaryTask4.model.User;

public interface UserService {

    User getByLogin(String login);

}
