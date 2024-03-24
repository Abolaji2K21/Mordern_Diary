package africa.semicolon.Mordern_Diary.data.repositories;

import africa.semicolon.Mordern_Diary.data.model.Diary;
import africa.semicolon.Mordern_Diary.data.model.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepositories extends MongoRepository<Entry, String> {
    List<Entry> findByUsernameAndDeleted(String username, boolean deleted);

//    List<Entry> findByUsername(String username);
//    List<Entry> findByTitleAndUsername(String title, String username);
//    List<Entry> findByUsername(String username);
//    Entry save(Entry myEntry);
//    List<Entry> findAll();
//    Entry findById(int entry_id);
//    List<Entry> findByUsername(String username);
//    long count();
//    void delete(int entry_Id);
//    void delete(Entry myEntry);

}
