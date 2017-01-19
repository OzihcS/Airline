package ua.nure.piontkovskyi.SummaryTask4.service.JDBCService;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Autowired;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Service;
import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.repository.FlightRepository;
import ua.nure.piontkovskyi.SummaryTask4.service.FlightService;

import java.util.List;

@Service
public class JDBCFlightService implements FlightService {

    @Autowired
    private FlightRepository repository;

    @Override
    public List<Flight> getAll() {
        return repository.getAll();
    }

    @Override
    public Flight getById(int id) {
        return repository.getById(id);
    }
}
