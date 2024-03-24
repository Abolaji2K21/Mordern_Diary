package africa.semicolon.Mordern_Diary.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
public class Diary {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean lock ;
    private boolean deleted;


}
