package ua.nure.piontkovskyi.SummaryTask4.service;

import ua.nure.piontkovskyi.SummaryTask4.annotation.Transactional;
import ua.nure.piontkovskyi.SummaryTask4.entity.Request;

import java.util.List;

public interface RequestService {

    List<Request> get(int id);

    @Transactional
    boolean add(Request request);

    @Transactional
    boolean update(Request request);

    boolean remove(int id);

}
