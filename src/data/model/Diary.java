package data.model;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private int id;
    private final List<Entry> entries = new ArrayList<>();


    public Diary() {}

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }



}
