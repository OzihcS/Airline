package ua.nure.piontkovskyi.SummaryTask4.service;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Cacheable;
import ua.nure.piontkovskyi.SummaryTask4.annotation.EvictCache;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Transactional;
import ua.nure.piontkovskyi.SummaryTask4.entity.Brigade;
import ua.nure.piontkovskyi.SummaryTask4.entity.Flight;
import ua.nure.piontkovskyi.SummaryTask4.entity.Staffer;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.StaffRole;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Status;

import java.util.List;

public interface FlightService {

    @Cacheable
    List<Flight> getAllFlights();

    Flight getById(int id);

    @Transactional
    @EvictCache
    boolean add(Flight flight);

    @Transactional
    @EvictCache
    boolean remove(int id);

    @Transactional
    @EvictCache
    boolean update(Flight flight);

    Brigade getBrigade(int id);

    Staffer getStaffer(int id);

    List<Staffer> getStaffersByRole(StaffRole role);

    @Transactional
    @EvictCache
    boolean chaneStatus(int id, Status status);

    @Transactional
    @EvictCache
    boolean setBrigade(int id, Brigade brigade);
}
