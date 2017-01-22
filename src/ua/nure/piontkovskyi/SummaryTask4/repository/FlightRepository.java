package ua.nure.piontkovskyi.SummaryTask4.repository;


import ua.nure.piontkovskyi.SummaryTask4.model.Brigade;
import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.model.Staffer;

import java.util.List;

public interface FlightRepository {


    List<Flight> getAll();

    Flight getById(int id);

    boolean add(Flight flight);

    boolean remove(int id);

    boolean update(Flight flight);

    Brigade getBrigade(int id);

    Staffer getStaffer(int id);

}
