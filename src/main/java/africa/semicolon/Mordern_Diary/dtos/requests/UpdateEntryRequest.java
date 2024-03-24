package africa.semicolon.Mordern_Diary.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEntryRequest {
//    @NotEmpty(message = "Username cannot be empty")
//    @NotNull(message = "Username cannot be null")
    private String userName;
    private String title;
    private String body;
    private String id;

}
