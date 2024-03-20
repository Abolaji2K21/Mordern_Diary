package data.repositories;

import data.model.Diary;
import data.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImplement implements EntryRepositories{
    private List<Entry> entries = new ArrayList<>();
    private int currentEntryId = 0;



    @Override
    public Entry save(Entry entry) {
        if (isNew(entry)) addIdTo(entry);
        else update(entry);

        entries.add(entry);

        return entry;
    }

    private void update(Entry entry) {
        for (int checkIndex = 0; checkIndex < entries.size(); checkIndex++)
            if (entries.get(checkIndex).getEntry_Id() == entry.getEntry_Id()){
                entries.set(checkIndex, entry);
                break;
            }
    }
    private void addIdTo(Entry entry) {
        entry.setEntry_Id(generateId());
    }

    private boolean isNew(Entry entry) {
        return entry.getEntry_Id() == 0;
    }

    private int generateId() {
        return currentEntryId += 1;
    }


    @Override
    public List<Entry> findAll() {

        return entries;
    }

    @Override
    public Entry findById(int entry_Id) {
        for(Entry entry: entries){
            if(entry.getEntry_Id() == entry_Id){
                return entry;
            }
        }
        return null;
    }

    @Override
    public List<Entry> findByUsername(String username) {
        List<Entry> foundEntries = new ArrayList<>();
        for(Entry entry : entries){
            if (entry.getUsername().equalsIgnoreCase(username)){
                foundEntries.add(entry);
            }
        }
        return foundEntries;
    }

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public void delete(int entry_Id) {
        Entry foundEntry = findById(entry_Id);
        entries.remove(foundEntry);
    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
    }
}
