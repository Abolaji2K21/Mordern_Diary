package data.repositories;

import data.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoriesImplement implements DiaryRepositories{


    private List<Diary> diaries = new ArrayList<>();
    private int currentDiaryId = 0;


    private int getCurrentDiaryId(){
        return currentDiaryId += 1;
    }


    public Diary save(Diary diary) {
        if (isNew(diary)) addIdTo(diary);
        else update(diary);
        diaries.add(diary);
        return diary;
    }

    private void update(Diary diary) {
        for (int checkIndex = 0; checkIndex < diaries.size(); checkIndex++)
            if (diaries.get(checkIndex).getId() == diary.getId()){
                diaries.set(checkIndex, diary);
                break;
            }
    }

    private void addIdTo(Diary diary) {
        diary.setId(getCurrentDiaryId());
    }

    private boolean isNew(Diary diary) {
        return diary.getId() == 0;
    }


    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public Diary findById(String username) {
        for (Diary myDiary : diaries)
            if (myDiary.getUsername().equals(username))
                return myDiary;
        return null;
    }

    @Override
    public long count() {
        return diaries.size();
    }


    @Override
    public void delete(String username) {
        Diary myDiary = findById(username);
//        for (Diary myDiary : diaries)
//            if (myDiary.getUsername().equals(username))
                diaries.remove(myDiary);
    }

    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
    }
}
