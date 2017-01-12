package ua.nure.piontkovskyi.SummaryTask4.service.JDBCService;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Autowired;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Service;
import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.repository.UserRepository;
import ua.nure.piontkovskyi.SummaryTask4.service.UserService;

@Service
public class JDBCUserService implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User getByLogin(String login) {
        return repository.getByLogin(login);
    }
}
