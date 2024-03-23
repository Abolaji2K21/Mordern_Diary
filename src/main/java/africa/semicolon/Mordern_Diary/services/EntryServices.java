package africa.semicolon.Mordern_Diary.services;

import africa.semicolon.Mordern_Diary.dtos.requests.CreateEntryRequest;
import org.springframework.stereotype.Service;

@Service
public interface EntryServices {
    void createEntry(CreateEntryRequest createEntryRequest);
//    void deleteEntryBy(int id);
//    Entry getEntryBy(int id);
//    List<Entry> getEntriesFor(String username);
}
