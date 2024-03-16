package data.repositories;

import data.model.Diary;
import data.model.Entry;

import java.util.List;

public interface EntryRepositories {
    Entry save(Entry myEntry);
    List<Entry> findAll();
    Entry findById(int entry_id);
    Entry findByUsername(String username);
    long count();
    void delete(int entry_Id);
    void delete(Entry myEntry);

}
