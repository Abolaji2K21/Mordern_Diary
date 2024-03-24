package africa.semicolon.Mordern_Diary.services;

import africa.semicolon.Mordern_Diary.data.model.Entry;
import africa.semicolon.Mordern_Diary.data.repositories.EntryRepositories;
import africa.semicolon.Mordern_Diary.dtos.requests.CreateEntryRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.UpdateEntryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntryServicesImplementation implements EntryServices {

    private final EntryRepositories entryRepositories;

    private final DiaryService diaryService;

    @Override
    public void createEntry(CreateEntryRequest createEntryRequest) {
        if (diaryService.findDiaryBy(createEntryRequest.getUsername()) != null) {
            Entry entry = new Entry();
            entry.setTitle(createEntryRequest.getTitle());
            entry.setBody(createEntryRequest.getBody());
            entry.setUsername(createEntryRequest.getUsername());
            entryRepositories.save(entry);
        } else {
            throw new IllegalArgumentException("User does not exist.");
        }
    }

    @Override
    public void updateEntryWith(UpdateEntryRequest request) {
        List<Entry> foundEntries = entryRepositories.findByTitleAndUsername(request.getTitle(), request.getUserName());
        if (!foundEntries.isEmpty()) {
            for (Entry entry : foundEntries) {
                entry.setTitle(request.getTitle());
                entry.setBody(request.getBody());
                entryRepositories.save(entry);
            }
        } else {
            throw new IllegalArgumentException("Entry not found.");
        }
    }

    @Override
    public void deleteEntryBy(String title, String username) {
        List<Entry> entriesToDelete = entryRepositories.findByTitleAndUsername(title, username);
        if (!entriesToDelete.isEmpty()) {
            for (Entry entry : entriesToDelete) {
                entry.setDeleted(true);
                entryRepositories.save(entry);
            }
        } else {
            throw new IllegalArgumentException("Entry not found.");
        }
    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        List<Entry> entries = entryRepositories.findByUsername(username);
        List<Entry> nonDeletedEntries = new ArrayList<>();

        for (Entry entry : entries) {
            if (!entry.isDeleted()) {
                nonDeletedEntries.add(entry);
            }
        }

        return nonDeletedEntries;
    }
}
