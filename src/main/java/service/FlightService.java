package service;

import annotation.Cacheable;
import annotation.EvictCache;
import annotation.Service;
import annotation.Transactional;
import entity.Brigade;
import entity.Flight;
import entity.Staffer;
import entity.enums.StaffRole;
import entity.enums.Status;

import java.util.List;

/**
 * Flight service
 */
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
    boolean updateFlight(Flight flight);

    Staffer getStaffer(int id);

    List<Staffer> getStaffersByRole(StaffRole role);

    @Transactional
    @EvictCache
    boolean chaneStatus(int id, Status status);

    @Transactional
    @EvictCache
    boolean setBrigade(int id, Brigade brigade);
}
