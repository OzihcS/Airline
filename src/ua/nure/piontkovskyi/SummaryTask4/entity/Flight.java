package ua.nure.piontkovskyi.SummaryTask4.entity;


import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Status;

import java.io.Serializable;
import java.util.Date;


/**
 * Entity class of flight
 */
public class Flight extends AbstractEntity {

    private String name;
    private String departureLocation;
    private String arriveLocation;
    private Status status;
    private Date departureDate;
    private Date arriveDate;
    private Brigade brigade;

    public Flight() {
    }

    public Flight(int id, String name, String departureLocation, String arriveLocation, Status status, Date departureDate, Date arriveDate, Brigade brigade) {
        this.id = id;
        this.name = name;
        this.departureLocation = departureLocation;
        this.arriveLocation = arriveLocation;
        this.status = status;
        this.departureDate = departureDate;
        this.arriveDate = arriveDate;
        this.brigade = brigade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArriveLocation() {
        return arriveLocation;
    }

    public void setArriveLocation(String arriveLocation) {
        this.arriveLocation = arriveLocation;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arrivaDate) {
        this.arriveDate = arrivaDate;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }
}
