package data.model;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private final String username;
    private final String password;
    private final List<Entry> entries = new ArrayList<>();

    public Diary(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
