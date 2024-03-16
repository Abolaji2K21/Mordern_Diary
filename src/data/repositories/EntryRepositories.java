package data.repositories;

import data.model.Diary;
import data.model.Entry;

import java.util.List;

public interface EntryRepositories {
    Entry save(Entry myEntry);
    List<Entry> findAll();
    Entry findById(int id);
    Entry findByUsername(String username);
    long count();
    void delete(String username);
    void delete(Entry myEntry);

}
