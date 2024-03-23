package africa.semicolon.Mordern_Diary.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Entry {
    @Id
    private String entry_Id;

    private String title;
    private String body;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private String username;


}
