//package data.repositories;
//
//import africa.semicolon.Mordern_Diary.data.model.Entry;
//import africa.semicolon.Mordern_Diary.data.repositories.EntryRepositoryImplement;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class EntryRepositoryImplementTest {
//
//
//    @Test
//    public void testSaveNewEntry() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry();
//        myRepository.save(entry);
////        assertEquals(1, entry.getEntry_Id());
//        assertEquals(1L, myRepository.count());
//
//    }
//
//    @Test
//    public void testSaveMultipleEntries() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry();
//        Entry entryOne = new Entry();
//        Entry entryTwo = new Entry();
//        myRepository.save(entry);
//        myRepository.save(entryOne);
//        myRepository.save(entryTwo);
//        assertEquals(3L, myRepository.count());
//
//    }
//
//    @Test
//    public void testUpdateExistingEntryTitle() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
//        entry.setTitle("Trying To Change Title");
//        myRepository.save(entry);
//        assertEquals("Trying To Change Title", entry.getTitle());
//    }
//
//    @Test
//    public void testUpdateExistingEntryBody() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
//        entry.setBody("Trying To Change Body");
//        myRepository.save(entry);
//        assertEquals("Trying To Change Body", entry.getBody());
//    }
//
//    @Test
//    public void testUpdateExistingAuthor() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My dairy");
//        entry.setUsername("My diary");
//        myRepository.save(entry);
//        assertEquals("My diary", entry.getUsername());
//    }
//
//    @Test
//    public void testFindEntryById() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
//        myRepository.save(entry);
//        assertEquals(entry, myRepository.findById(1));
//
//    }
//    @Test
//    public void testFindEntryByNonexistentNegativeId() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        assertNull(myRepository.findById(-100));
//    }
//
//    @Test
//    public void testFindEntryByUsername() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
//        myRepository.save(entry);
//        assertEquals(entry, myRepository.findByUsername("My diary"));
//    }
//
//
//    @Test
//    public void testFindEntryByNonexistentUsername() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
//        Entry entryOne = new Entry(1, "Iam_Tired", "And Frustrated", "Duplicate");
//        myRepository.save(entry);
//        assertEquals(1L, myRepository.count());
//    }
//
//    @Test
//    public void testFindAllEntries(){
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry();
//        Entry entryOne = new Entry();
//        Entry entryTwo = new Entry();
//        myRepository.save(entry);
//        myRepository.save(entryOne);
//        myRepository.save(entryTwo);
//        assertEquals(3L, myRepository.count());
//        Entry[] expected = {entry, entryOne, entryTwo};
//        Entry[] actual = myRepository.findAll().toArray(new Entry[0]);
//        assertArrayEquals(expected, actual);
//
//    }
//
//    @Test
//    public void testDeleteEntryById() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry();
//        myRepository.save(entry);
//        myRepository.delete(1);
//        assertEquals(0L, myRepository.count());
//
//    }
//
//    @Test
//    public void testDeleteEntryByEntry() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
//        myRepository.save(entry);
//        myRepository.delete(entry);
//        assertEquals(0L, myRepository.count());
//
//    }
//
//    @Test
//    public void testDeleteANonExistingEntryByEntry() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
//        Entry entryOne = new Entry(2, "duplicate", "duplicate", "duplicate");
//        myRepository.save(entry);
//        myRepository.delete(entryOne);
//        assertEquals(1L, myRepository.count());
//
//    }
//
//    @Test
//    public void testDeleteANonExistingEntryById() {
//        EntryRepositoryImplement myRepository = new EntryRepositoryImplement();
//        Entry entry = new Entry(1, "Iam_Tired", "And Frustrated", "My diary");
//        Entry entryOne = new Entry(2, "duplicate", "duplicate", "duplicate");
//        myRepository.save(entry);
//        myRepository.delete(2);
//        assertEquals(1L, myRepository.count());
//
//    }
//
//
//
//
//
//}