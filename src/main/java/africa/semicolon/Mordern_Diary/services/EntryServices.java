package africa.semicolon.Mordern_Diary.services;

import africa.semicolon.Mordern_Diary.data.model.Entry;
import africa.semicolon.Mordern_Diary.dtos.requests.CreateEntryRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.UpdateEntryRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EntryServices {
    void createEntry(CreateEntryRequest createEntryRequest);
    void updateEntryWith(UpdateEntryRequest request);
    void deleteEntryBy(UpdateEntryRequest request);
    List<Entry> getEntriesFor(String username);


}
