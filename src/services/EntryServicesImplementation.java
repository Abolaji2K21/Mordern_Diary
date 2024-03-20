package services;

import data.model.Entry;
import data.repositories.DiaryRepositories;
import data.repositories.EntryRepositories;
import data.repositories.EntryRepositoryImplement;

import java.util.List;

public class EntryServicesImplementation implements EntryServices {

    private static EntryRepositories entryRepositories = new EntryRepositoryImplement();


    @Override
    public void save(Entry entry) {
        entryRepositories.save(entry);
    }

    @Override
    public void deleteEntryBy(int id) {
        entryRepositories.delete(id);

    }

    @Override
    public Entry getEntryBy(int id) {
        Entry entry = entryRepositories.findById(id);
        if (entry == null) throw new IllegalStateException("Entry not found");
        return entry;
    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        List<Entry> entries = entryRepositories.findByUsername(username.toLowerCase());
        if (entries.isEmpty()) throw new IllegalStateException("No entry found");
        return entries;
    }

}

