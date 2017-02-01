package ua.nure.piontkovskyi.SummaryTask4.repository;

import ua.nure.piontkovskyi.SummaryTask4.entity.AdminStatistic;
import ua.nure.piontkovskyi.SummaryTask4.entity.Request;

import java.util.List;

/**
 * Request repository class which contain methods interaction with request table in DB.
 */
public interface RequestRepository {

    List<Request> get(int id);

    boolean update(Request request);

    boolean add(Request request);

    boolean remove(int id);

    boolean updateStatistic(int id, AdminStatistic statistic);

    AdminStatistic getStatistic(int id);

}
