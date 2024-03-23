package africa.semicolon.Mordern_Diary.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document
//@AllArgsConstructor
//@Builder
@ToString
public class Diary {
    @Id
    private String id;

    private String username;

    private String password;
//    private int id;
//    private List<Entry> entries = new ArrayList<>();
    private boolean lock ;
    private boolean deleted;

//private void get(){
//    Diary diary = new Diary();
//diary.setUsername();
//diary.setId(id);
//}
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public List<Entry> getEntries() {
//        return entries;
//    }
//
//    public void setEntries(List<Entry> entries) {
//        this.entries = entries;
//    }
//
//    public boolean isLock() {
//        return lock;
//    }
//
//    public void setLock(boolean lock) {
//        this.lock = lock;
//    }
}
