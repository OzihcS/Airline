package service;

import annotation.Cacheable;
import annotation.EvictCache;
import annotation.Transactional;
import entity.User;

import java.util.List;

/**
 * User service
 */
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

    @Cacheable
    List<User> getAllUsers();

    List<User> getAdmins();

    User getById(int i);
}
