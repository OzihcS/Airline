package ua.nure.piontkovskyi.SummaryTask4.entity;

import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Role;

import java.io.Serializable;

/**
 * Entity class of user
 */
public class User extends AbstractEntity {

    private String name;
    private String login;
    private String password;
    private Role role;

    public User() {
    }

    public User(int id, String name, String login, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
