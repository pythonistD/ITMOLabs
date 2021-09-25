package database;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String userName;
    private String userPass;

    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(userPass, user.userPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPass);
    }
}
