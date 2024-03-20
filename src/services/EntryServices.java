package services;

import data.model.Entry;

import java.util.List;

public interface EntryServices {
    void save(Entry entry);
    void deleteEntryBy(int id);
//    Entry getEntryBy(int id);
    List<Entry> getEntriesFor(String username);
}
