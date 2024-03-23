//package data.repositories;
//
//import africa.semicolon.Mordern_Diary.data.model.Diary;
//import africa.semicolon.Mordern_Diary.data.repositories.DiaryRepositoriesImplement;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DiaryRepositoriesTest {
//
//
//    @Test
//    void testThatMyDefaultDiaryRepositoryIsZero() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        assertEquals(0L, myRepositories.count());
//
//    }
//
//    @Test
//    void testThatMyDefaultDiaryRepositoryIsZeroIfIDontSave() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDairy = new Diary("userName", "password");
//        assertEquals(0L, myRepositories.count());
//    }
//
//    @Test
//    void testThatICanSaveOneDiary() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDairy = new Diary("userName", "password");
//        myRepositories.save(myDairy);
//        assertEquals(1L, myRepositories.count());
//    }
//
//    @Test
//    void testThatMyICanSaveMultipleDiary() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDairy = new Diary("userName", "password");
//        Diary mohDiary = new Diary("Ayinla", "password");
//        myRepositories.save(mohDiary);
//        myRepositories.save(myDairy);
//        assertEquals(2L, myRepositories.count());
//
//    }
//
//    @Test
//    public void testSetAndGetId() {
//        Diary myDairy = new Diary();
//        myDairy.setId(1);
//        assertEquals(1, myDairy.getId());
//    }
//
//    @Test
//    public void testSetAndGetUsername() {
//        Diary myDairy = new Diary();
//        myDairy.setUsername("Abike");
//        assertEquals("Abike", myDairy.getUsername());
//    }
//
//    @Test
//    public void testFindDiaryByUsername() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDairy = new Diary("userName", "password");
//        myRepositories.save(myDairy);
//        assertEquals(myDairy, myRepositories.findById("userName"));
//    }
//
//    @Test
//    public void testFindDiaryByNonexistentUsername() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        assertNull(myRepositories.findById("Nonexistent"));
//    }
//
//    @Test
//    public void testFindAllDiaries() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDiary = new Diary("userName", "password");
//        Diary dayoDiary = new Diary("Adisa", "password");
//        Diary mohDiary = new Diary("Ayinla", "password");
//        Diary abikeDiary = new Diary("Abike", "password");
//        myRepositories.save(myDiary);
//        myRepositories.save(dayoDiary);
//        myRepositories.save(mohDiary);
//        myRepositories.save(abikeDiary);
//        Diary[] expected = {myDiary, dayoDiary, mohDiary, abikeDiary};
//        Diary[] actual = myRepositories.findAll().toArray(new Diary[0]);
//
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    public void testDeleteDiaryByUsername() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDairy = new Diary("userName", "password");
//        myRepositories.save(myDairy);
//        assertEquals(1L, myRepositories.count());
//        myRepositories.delete("userName");
//        assertEquals(0L, myRepositories.count());
//
//    }
//
//    @Test
//    public void testDeleteDiary() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDairy = new Diary("userName", "password");
//        myRepositories.save(myDairy);
//        assertEquals(1L, myRepositories.count());
//        myRepositories.delete(myDairy);
//        assertEquals(0L, myRepositories.count());
//    }
//
//
//    @Test
//    public void testDeleteNonexistentDiary() {
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDairy = new Diary("userName", "password");
//        Diary mohDiary = new Diary("Ayinla", "password");
//        myRepositories.save(myDairy);
//        assertEquals(1L, myRepositories.count());
//        myRepositories.delete(mohDiary);
//        assertEquals(1L, myRepositories.count());
//
//    }
//
//    @Test
//    public void testFindById_ReturnsTheDiaryFound(){
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDairy = new Diary("userName", "password");
//        Diary mohDiary = new Diary("Ayinla", "password");
//        myRepositories.save(myDairy);
//        myRepositories.save(mohDiary);
//        assertEquals(2L,myRepositories.count());
//        assertSame(myDairy, myRepositories.findById("userName"));
//        assertSame(mohDiary, myRepositories.findById("Ayinla"));
//
//    }
//
//    @Test
//    public void saveFourDiaries_DeleteTwoDiaryObjectTest(){
//        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
//        Diary myDiary = new Diary("userName", "password");
//        Diary dayoDiary = new Diary("Adisa", "password");
//        Diary mohDiary = new Diary("Ayinla", "password");
//        Diary abikeDiary = new Diary("Abike", "password");
//        myRepositories.save(myDiary);
//        myRepositories.save(dayoDiary);
//        myRepositories.save(mohDiary);
//        myRepositories.save(abikeDiary);
//
//        assertEquals(4L,myRepositories.count());
//        myRepositories.delete(mohDiary);
//        myRepositories.delete(abikeDiary);
//        assertEquals(2L,myRepositories.count());
//
//    }
//
//
//
//}