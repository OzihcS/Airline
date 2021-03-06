package service.JDBCService;

import annotation.Autowired;
import annotation.Service;
import entity.User;
import repository.UserRepository;
import service.UserService;

import java.util.List;

/**
 * Implementation of UserService
 */

@Service
public class JDBCUserService implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User getByLogin(String login) {
        return repository.getByLogin(login);
    }

    @Override
    public boolean add(User user) {
        return repository.add(user);
    }

    @Override
    public boolean remove(int id) {
        return repository.remove(id);
    }

    @Override
    public boolean update(User user) {
        return repository.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAll();
    }

    @Override
    public List<User> getAdmins() {
        return repository.getAdmins();
    }

    @Override
    public User getById(int i) {
        return repository.getById(i);
    }
}
