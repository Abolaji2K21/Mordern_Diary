package africa.semicolon.Mordern_Diary.controllers;

import africa.semicolon.Mordern_Diary.data.model.Diary;
import africa.semicolon.Mordern_Diary.dtos.requests.LoginRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.LogoutRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.RegisterRequest;
import africa.semicolon.Mordern_Diary.services.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryServices;

//    public DiaryController(DiaryRepositories diaryRepositories, EntryServices entryServices) {
//        this.diaryServices = new DiaryServiceImplementation(diaryRepositories, entryServices);
//    }

    @PostMapping
    public String registerUser(@RequestBody RegisterRequest request) {
        try {
            diaryServices.registerUser(request);
            return "registration successful";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        try {
            diaryServices.login(request);
            return "login successful";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/logout")
    public String logout(@RequestBody LogoutRequest request){
        try {
            diaryServices.logout(request.getUsername());
            return "logout successful";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping
    public List<Diary> test() {
        return diaryServices.getAllDiaries();
    }
//    public String removeUserWith(RemoveUserRequest request) {
//        try {
//            diaryServices.removeUser(request);
//            return "removed successfully";
//        } catch (IllegalStateException e) {
//            return e.getMessage();
//        }
//    }
//
//    public String createEntry(CreateEntryRequest request) {
//        try {
//            diaryServices.createEntryWith(request);
//            return "created successfully";
//        } catch (IllegalStateException e) {
//            return e.getMessage();
//        }
//    }
//
//    public String updateEntry(UpdateEntryRequest request) {
//        try {
//            diaryServices.updateEntryWith(request);
//            return "updated successfully";
//        } catch (IllegalStateException e) {
//            return e.getMessage();
//        }
//    }
}
