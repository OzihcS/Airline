package ua.nure.piontkovskyi.SummaryTask4.service.JDBCService;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Autowired;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Service;
import ua.nure.piontkovskyi.SummaryTask4.entity.Request;
import ua.nure.piontkovskyi.SummaryTask4.repository.RequestRepository;
import ua.nure.piontkovskyi.SummaryTask4.service.RequestService;

import java.util.List;

@Service
public class JDBCRequestService implements RequestService {

    @Autowired
    RequestRepository repository;


    @Override
    public List<Request> get(int id) {
        return repository.get(id);
    }

    @Override
    public boolean add(Request request) {
        return repository.add(request);
    }

    @Override
    public boolean update(Request request) {
        return repository.update(request);
    }

    @Override
    public boolean remove(int id) {
        return repository.remove(id);
    }
}
