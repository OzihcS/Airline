package util;


import entity.Flight;
import entity.Request;
import entity.User;
import entity.enums.RequestStatus;
import entity.enums.Role;

import java.util.List;

/**
 * Contains functions used for EL function in JSP.
 **/
public final class Functions {


    /**
     * Check user role
     *
     * @param user
     * @return true if user is dispatcher
     */
    public static boolean isDispatcher(User user) {
        if (user != null && user.getRole().equals(Role.DISPATCHER)) {
            return true;
        }
        return false;
    }

    /**
     * Check user role
     *
     * @param user
     * @return true if user is admin
     */
    public static boolean isAdmin(User user) {
        if (user != null && user.getRole().equals(Role.ADMINISTRATOR)) {
            return true;
        }
        return false;
    }


    /**
     * Check request status
     *
     * @param request
     * @return true if request is unconfirmed
     */
    public static boolean isUnconfirmed(Request request) {
        if (request.getStatus().equals(RequestStatus.UNCONFIRMED)) {
            return true;
        }
        return false;
    }

    public static boolean isFlightUnconfirmed(Flight flight) {
        if (flight != null && flight.getStatus().equals("UNCONFIRMED")) {
            return true;
        }
        return false;
    }

    /**
     * Check flight status
     *
     * @param flight
     * @return true if flight is finished
     */
    public static boolean isFinished(Flight flight) {
        if (flight != null && flight.getStatus().equals("FINISHED")) {
            return true;
        }
        return false;
    }

    public static boolean isInProgress(Flight flight) {
        if (flight != null && flight.getStatus().equals("IN_PROGRESS")) {
            return true;
        }
        return false;
    }

    public static boolean isExecuted(Request request) {
        if (request != null && request.getStatus().equals(RequestStatus.EXECUTED)) {
            return true;
        }
        return false;
    }

    public static boolean isRejected(Request request) {
        if (request != null && request.getStatus().equals(RequestStatus.REJECTED)) {
            return true;
        }
        return false;
    }


    public static boolean isEmpty(List list) {
        if (list == null || list.size() == 0 || list.isEmpty()) {
            return true;
        }
        return false;
    }


}
