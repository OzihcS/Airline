package ua.nure.piontkovskyi.SummaryTask4.service;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Cacheable;
import ua.nure.piontkovskyi.SummaryTask4.annotation.EvictCache;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Transactional;
import ua.nure.piontkovskyi.SummaryTask4.model.Brigade;
import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.model.Staffer;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.StaffRole;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Status;

import java.util.List;

public interface FlightService {

    //    @Cacheable
    List<Flight> getAll();

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

    @Cacheable
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
