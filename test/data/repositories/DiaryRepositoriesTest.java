package data.repositories;

import data.model.Diary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoriesTest {

    @Test
    void testThatICanSave(){
        DiaryRepositoriesImplement myRepositories = new DiaryRepositoriesImplement();
        Diary myDairy = new Diary("userName","password");
        myRepositories.save(myDairy);
        assertEquals(1L, myRepositories.count());
    }

}