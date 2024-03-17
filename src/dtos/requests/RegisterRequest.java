package dtos.requests;

import java.util.Locale;

public class RegisterRequest {
    private String username;
    private String password;

    public void setUsername(String username) {
        if (username != null) {
            this.username = username.toLowerCase();
        } else {
            this.username = null;
        }
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
