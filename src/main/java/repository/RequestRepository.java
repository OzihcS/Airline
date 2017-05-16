package repository;

import entity.AdminStatistic;
import entity.Request;

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
