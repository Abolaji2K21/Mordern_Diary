package africa.semicolon.Mordern_Diary.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Locale;
@Data
public class RegisterRequest {
    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    private String password;


}
