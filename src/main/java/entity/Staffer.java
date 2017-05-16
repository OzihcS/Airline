package entity;

import entity.enums.StaffRole;

/**
 * Entity class of staffer
 */
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
