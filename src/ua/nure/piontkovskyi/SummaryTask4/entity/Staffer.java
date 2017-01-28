package ua.nure.piontkovskyi.SummaryTask4.entity;


import ua.nure.piontkovskyi.SummaryTask4.entity.enums.StaffRole;

import java.io.Serializable;

public class Staffer extends AbstractEntity {

    private String firstName;
    private String lastName;
    private StaffRole role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role.toString();
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }
}
