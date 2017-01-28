package ua.nure.piontkovskyi.SummaryTask4.repository;

import ua.nure.piontkovskyi.SummaryTask4.entity.Request;

import java.util.List;

public interface RequestRepository {

    List<Request> get(int id);

    boolean update(Request request);

    boolean add(Request request);

}
