package data.repositories;

import data.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoriesImplement implements DiaryRepositories{


    private List<Diary> diaries = new ArrayList<>();

    public Diary save(Diary diary) {
        return null;
    }


    @Override
    public List<Diary> findAll() {
        return null;
    }

    @Override
    public Diary findById(String username) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }



    @Override
    public void delete(String username) {

    }

    @Override
    public void delete(Diary diary) {

    }
}
