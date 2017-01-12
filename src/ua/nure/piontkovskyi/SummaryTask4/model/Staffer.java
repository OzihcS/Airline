package ua.nure.piontkovskyi.SummaryTask4.model;


import ua.nure.piontkovskyi.SummaryTask4.model.enums.StaffRole;

public class Staffer {

    private int id;
    private String firstName;
    private String lastName;
    private StaffRole role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
