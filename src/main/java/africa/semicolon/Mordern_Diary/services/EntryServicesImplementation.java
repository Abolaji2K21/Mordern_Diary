package africa.semicolon.Mordern_Diary.services;

import africa.semicolon.Mordern_Diary.data.model.Diary;
import africa.semicolon.Mordern_Diary.data.model.Entry;
import africa.semicolon.Mordern_Diary.data.repositories.EntryRepositories;
import africa.semicolon.Mordern_Diary.dtos.requests.CreateEntryRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.UpdateEntryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntryServicesImplementation implements EntryServices {
    @Autowired
    private EntryRepositories entryRepositories;
    @Autowired
    private DiaryService diaryService;

    @Override
    public void createEntry(CreateEntryRequest createEntryRequest) {
        Diary diary = diaryService.findDiaryBy(createEntryRequest.getUsername().toLowerCase());
        if (diary != null) {
            Entry entry = new Entry();
            entry.setTitle(createEntryRequest.getTitle());
            entry.setBody(createEntryRequest.getBody());
            entry.setUsername(createEntryRequest.getUsername().toLowerCase());
            entryRepositories.save(entry);
        } else {
            throw new IllegalArgumentException("User does not exist.");
        }
    }

    @Override
    public void updateEntryWith(UpdateEntryRequest request) {
        Optional<Entry> foundEntry = entryRepositories.findById(request.getId());
        if (foundEntry.isPresent()) {
            Entry entry = foundEntry.get();
            entry.setTitle(request.getTitle());
            entry.setBody(request.getBody());
            entryRepositories.save(entry);
        } else {
            throw new IllegalArgumentException("Entry not found.");
        }
    }

//    @Override
//    public void deleteEntryBy(String title, String username) {
//
//    }


    @Override
    public void deleteEntryBy(UpdateEntryRequest request) {
        Optional<Entry> foundEntry = entryRepositories.findById(request.getId());
        if (foundEntry.isPresent()) {
            Entry myEntry = foundEntry.get();
            myEntry.setDeleted(true);
            entryRepositories.save(myEntry);
        } else {
            throw new IllegalArgumentException("Entry not found.");
        }
    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        boolean deleted = false;
        return entryRepositories.findByUsernameAndDeleted(username,deleted );
    }
//        Optional<Entry> entries = entryRepositories.findByUsername(username);
//        List<Entry> nonDeletedEntries = new ArrayList<>();
//
//        if (entries.isPresent()) {
//            Entry myEntry = entries.get();
//            List<Entry> diaryEntries = myEntry.getEntries();
//            for (Entry entry : diaryEntries) {
//                if (!entry.isDeleted()) {
//                    nonDeletedEntries.add(entry);
//
//                }
//            }
//
//        }
//        return nonDeletedEntries;
//
//    }
}
