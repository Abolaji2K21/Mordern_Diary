package africa.semicolon.Mordern_Diary.controllers;

import africa.semicolon.Mordern_Diary.data.model.Diary;
import africa.semicolon.Mordern_Diary.data.model.Entry;
import africa.semicolon.Mordern_Diary.dtos.requests.*;
import africa.semicolon.Mordern_Diary.services.DiaryService;
import africa.semicolon.Mordern_Diary.services.EntryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


//import java.util.ArrayList;
//import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryServices;
    private final EntryServices entryServices;


    @PostMapping
    public String registerUser(@RequestBody RegisterRequest request) {
        try {
            diaryServices.registerUser(request);
            return "registration successful";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        try {
            diaryServices.login(request);
            return "login successful";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/logout")
    public String logout(@RequestBody LogoutRequest request){
        try {
            diaryServices.logout(request.getUsername());
            return "logout successful";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @GetMapping
    public List<Diary> getAllDiaries() {
        return diaryServices.getAllDiaries();
    }
    @PostMapping("/remove-user")
    public String removeUserWith(@RequestBody RemoveUserRequest request) {
        try {
            diaryServices.removeUser(request);
            return "removed successfully";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/create-entry")
    public String createEntry(@RequestBody CreateEntryRequest request) {
        try {
            entryServices.createEntry(request);
            return "created successfully";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/update-entry")
    public String updateEntry(@RequestBody UpdateEntryRequest request) {
        try {
            entryServices.updateEntryWith(request);
            return "updated successfully";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/entries")
    public List<Entry> getEntriesFor(@RequestParam String username) {
        return entryServices.getEntriesFor(username);
    }


    @PostMapping("/delete-entry")
    public String deleteEntry(@RequestBody UpdateEntryRequest request) {
        try {
            entryServices.deleteEntryBy(request);
            return ("Entry deleted successfully.");
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }




}
