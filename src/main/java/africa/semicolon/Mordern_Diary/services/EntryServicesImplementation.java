package africa.semicolon.Mordern_Diary.services;

import africa.semicolon.Mordern_Diary.data.model.Entry;
import africa.semicolon.Mordern_Diary.data.repositories.EntryRepositories;
import africa.semicolon.Mordern_Diary.dtos.requests.CreateEntryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServicesImplementation implements EntryServices {

    @Autowired
    private EntryRepositories entryRepositories;

    @Autowired
    private DiaryService diaryService;

    @Override
    public void createEntry(CreateEntryRequest createEntryRequest) {
        if (diaryService.userExists(createEntryRequest.getUsername())) {
            Entry entry = new Entry();
            entry.setTitle(createEntryRequest.getTitle());
            entry.setBody(createEntryRequest.getBody());
            entry.setUsername(createEntryRequest.getUsername());
            entryRepositories.save(entry);
        }
        throw new RuntimeException();
    }

    public List<Entry> getAllEntries(String username) {
        return entryRepositories.findByUsername(username);
    }

//    @Override
//    public void deleteEntryBy(int id) {
//        entryRepositories.delete(id);
//    }
//
//    @Override
//    public Entry getEntryBy(int id) {
//        Entry entry = entryRepositories.findById(id);
//        if (entry == null) throw new IllegalStateException("Entry not found");
//        return entry;
//    }
//
//    @Override
//    public List<Entry> getEntriesFor(String username) {
//        return entryRepositories.findByUsername(username.toLowerCase());
//    }

}

