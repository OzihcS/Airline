package ua.nure.piontkovskyi.SummaryTask4.service;


import ua.nure.piontkovskyi.SummaryTask4.annotation.Cacheable;
import ua.nure.piontkovskyi.SummaryTask4.annotation.EvictCache;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Transactional;
import ua.nure.piontkovskyi.SummaryTask4.model.User;

import java.util.List;

public interface UserService {

    User getByLogin(String login);

    @Transactional
    @EvictCache
    boolean add(User user);

    @Transactional
    @EvictCache
    boolean remove(int id);

    @Transactional
    @EvictCache
    boolean update(User user);

//    @Cacheable
    List<User> getAll();

    List<User> getAdmins();
}
