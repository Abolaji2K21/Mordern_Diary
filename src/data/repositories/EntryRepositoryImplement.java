package data.repositories;

import data.model.Diary;
import data.model.Entry;

import java.util.List;

public class EntryRepositoryImplement implements EntryRepositories{
    @Override
    public Entry save(Entry myEntry) {
        return null;
    }

    @Override
    public List<Entry> findAll() {
        return null;
    }

    @Override
    public Entry findById(int id) {
        return null;
    }

    @Override
    public Entry findByUsername(String username) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public void delete(Entry myEntry) {

    }
}
