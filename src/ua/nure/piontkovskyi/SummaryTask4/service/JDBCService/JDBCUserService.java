package ua.nure.piontkovskyi.SummaryTask4.service.JDBCService;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Autowired;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Service;
import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.repository.UserRepository;
import ua.nure.piontkovskyi.SummaryTask4.service.UserService;

import java.util.List;

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
    public List<User> getAll() {
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
