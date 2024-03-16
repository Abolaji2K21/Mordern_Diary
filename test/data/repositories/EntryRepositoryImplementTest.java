package data.repositories;

import data.model.Entry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryImplementTest {


    @Test
    public void testSaveNewEntry() {
        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
        Entry entry = new Entry();
        myRepository.save(entry);
//        assertEquals(1, entry.getEntry_Id());
        assertEquals(1L, myRepository.count());

    }

    @Test//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");

    public void testSaveMultipleEntries() {
        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
        Entry entry = new Entry();
        Entry entryOne = new Entry();
        Entry entryTwo = new Entry();
        myRepository.save(entry);
        myRepository.save(entryOne);
        myRepository.save(entryTwo);
        assertEquals(3L, myRepository.count());

    }

    @Test
    public void testUpdateExistingEntryTitle() {
        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
        entry.setTitle("Trying To Change Title");
        myRepository.save(entry);
        assertEquals("Trying To Change Title", entry.getTitle());
    }

    @Test
    public void testUpdateExistingEntryBody() {
        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
        entry.setBody("Trying To Change Body");
        myRepository.save(entry);
        assertEquals("Trying To Change Body", entry.getBody());
    }


}