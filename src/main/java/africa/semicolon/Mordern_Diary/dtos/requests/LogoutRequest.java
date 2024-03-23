package africa.semicolon.Mordern_Diary.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LogoutRequest {
    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    private String username;

}
