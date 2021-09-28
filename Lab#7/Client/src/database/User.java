package database;

import java.io.Serializable;

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
        return this.name;
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

    public void setTarget(String target) {
        this.target = target;
    }
}
