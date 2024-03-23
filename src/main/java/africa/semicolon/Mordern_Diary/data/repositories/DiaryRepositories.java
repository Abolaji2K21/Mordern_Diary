package africa.semicolon.Mordern_Diary.data.repositories;

import africa.semicolon.Mordern_Diary.data.model.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryRepositories extends MongoRepository<Diary, String> {


//    Diary findByUsernameAndId(String userName, String id);
    Optional<Diary> findByUsername(String username);
//    Diary findBySurnameAndId(String userName, String id);
//    Diary save(Diary diary);
//    List<Diary> findAll();
//    Diary findById(String username);
//    long count();
//    void delete(String username);
//    void delete(Diary diary);

}



