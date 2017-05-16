package repository;


import entity.Brigade;
import entity.Flight;
import entity.Staffer;
import entity.enums.StaffRole;
import entity.enums.Status;

import java.util.List;

/**
 * Flight repository class which contain methods interaction with flight table in DB.
 */
public interface FlightRepository {

    List<Flight> getAll();

    Flight getById(int id);

    boolean add(Flight flight);

    boolean remove(int id);

    boolean updateFlight(Flight flight);

    Brigade getBrigade(int id);

    Staffer getStaffer(int id);

    List<Staffer> getStaffersByRole(StaffRole role);

    boolean chaneStatus(int id, Status status);

    boolean setBrigade(int id, Brigade brigade);

}
