package ua.nure.piontkovskyi.SummaryTask4.service;

import ua.nure.piontkovskyi.SummaryTask4.annotation.EvictCache;
import ua.nure.piontkovskyi.SummaryTask4.annotation.Transactional;
import ua.nure.piontkovskyi.SummaryTask4.model.Request;

import java.util.List;

public interface RequestService {

    List<Request> get(int id);

    @Transactional
    @EvictCache
    boolean add(Request request);

    //    @Transactional
    @EvictCache
    boolean update(Request request);

}
