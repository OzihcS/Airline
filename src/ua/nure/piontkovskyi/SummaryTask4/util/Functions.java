package ua.nure.piontkovskyi.SummaryTask4.util;

import ua.nure.piontkovskyi.SummaryTask4.entity.Flight;
import ua.nure.piontkovskyi.SummaryTask4.entity.Request;
import ua.nure.piontkovskyi.SummaryTask4.entity.User;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.RequestStatus;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Role;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Status;

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
        if (user.getRole().equals(Role.DISPATCHER)) {
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
        if (user.getRole().equals(Role.ADMINISTRATOR)) {
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

    /**
     * Check flight status
     *
     * @param flight
     * @return true if flight is finished
     */
    public static boolean isFinished(Flight flight) {
        if (flight != null && flight.getStatus().equals(Status.FINISHED)) {
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
