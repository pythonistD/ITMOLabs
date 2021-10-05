package database;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 101L;
    private String name;
    private String pass;
    private String target;


    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && pass.equals(user.pass) && target.equals(user.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pass, target);
    }
}
