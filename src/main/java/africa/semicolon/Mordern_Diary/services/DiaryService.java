package africa.semicolon.Mordern_Diary.services;

import africa.semicolon.Mordern_Diary.data.model.Diary;
import africa.semicolon.Mordern_Diary.dtos.requests.LoginRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.RegisterRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.RemoveUserRequest;

public interface DiaryService {
    void registerUser(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);

    long getNumberOfUsers();

    //    long getNumberOfUsers();
    Diary findDiaryBy(String username);
    void logout(String username);

    void removeUser(RemoveUserRequest request);

    boolean userExists(String username);


//    void removeUser(RemoveUserRequest request);
//    void createEntryWith(CreateEntryRequest request);
//    void updateEntryWith( UpdateEntryRequest request);
//    void deleteEntryBy(int id, String username);



}
