package ua.nure.piontkovskyi.SummaryTask4.service.JDBCService;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Autowired;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Service;
import ua.nure.piontkovskyi.SummaryTask4.model.Brigade;
import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.model.Staffer;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.StaffRole;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Status;
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

    @Override
    public boolean add(Flight flight) {
        return repository.add(flight);
    }

    @Override
    public boolean remove(int id) {
        return repository.remove(id);
    }

    @Override
    public boolean update(Flight flight) {
        return repository.update(flight);
    }

    @Override
    public Brigade getBrigade(int id) {
        return repository.getBrigade(id);
    }

    @Override
    public Staffer getStaffer(int id) {
        return repository.getStaffer(id);
    }

    @Override
    public List<Staffer> getStaffersByRole(StaffRole role) {
        return repository.getStaffersByRole(role);
    }

    @Override
    public boolean chaneStatus(int id, Status status) {
        return repository.chaneStatus(id, status);
    }

    @Override
    public boolean setBrigade(int id, Brigade brigade) {
        return repository.setBrigade(id, brigade);
    }
}
