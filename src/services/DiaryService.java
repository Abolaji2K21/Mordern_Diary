package services;

import data.model.Diary;
import dtos.requests.*;

public interface DiaryService {
    void registerUser(RegisterRequest registerRequest);
    boolean login(LoginRequest loginRequest);
    long getNumberOfUsers();
    Diary findDiaryBy(String username);
    void logout(String username);
    void removeUser(RemoveUserRequest request);
    void createEntryWith(CreateEntryRequest request);
    void updateEntryWith(UpdateEntryRequest request);
    void deleteEntryBy(int id, String username);



}
