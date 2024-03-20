package services;

import data.model.Diary;
import data.model.Entry;
import data.repositories.DiaryRepositories;
import data.repositories.EntryRepositories;
import dtos.requests.*;

import java.util.List;

public class DiaryServiceImplementation implements DiaryService {
        private boolean loginUser = false;
        private static DiaryRepositories myRepository;
        private static EntryServices entryServices;

        public DiaryServiceImplementation(DiaryRepositories myRepository, EntryServices entryServices) {
            this.myRepository = myRepository;
            this.entryServices = entryServices;
        }
    @Override
    public void registerUser(RegisterRequest registerRequest) {
        if (validateRegistration(registerRequest)) {
            Diary user = new Diary();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(registerRequest.getPassword());

            myRepository.save(user);
        } else {
            throw new IllegalArgumentException("Registration failed");
        }
    }


    @Override
    public boolean login(LoginRequest loginRequest) {

        boolean isValidLogin = validateLogin(loginRequest);
        if (isValidLogin) {
            loginUser = true;
        }
        return isValidLogin;
    }

    @Override
    public long getNumberOfUsers() {
        return myRepository.count();
    }

    @Override
    public Diary findDiaryBy(String username) {
        Diary foundDiary = myRepository.findById(username.toLowerCase());
        if (foundDiary == null) throw new IllegalArgumentException("User not found.");
        return foundDiary;

    }

    @Override
    public void logout(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        if (foundDiary != null) {
            myRepository.save(foundDiary);
            loginUser = false;
        }


    }


    @Override
    public void removeUser(RemoveUserRequest request) {
        Diary myDiary = findDiaryBy(request.getUsername().toLowerCase());
        if (myDiary.isLocked()) throw new IllegalStateException("You need to login to use this service.");

        if (isPasswordIncorrect(myDiary, request.getPassword())) throw new IllegalArgumentException("Password is incorrect.");

        myRepository.delete(myDiary);

    }


    private static boolean isPasswordIncorrect(Diary foundDiary, String password) {
        return !foundDiary.getPassword().equals(password);
    }


    @Override
    public void createEntryWith(CreateEntryRequest request) {
        Diary myDiary = findDiaryBy(request.getUsername().toLowerCase());

//        if (myDiary == null) {
//            throw new IllegalArgumentException("Diary does not exist.");
//        }

        if (myDiary.isLocked()) {
            throw new IllegalStateException("Diary is locked. You need to login to use this service.");
        }

        Entry entry = createEntryFromRequest(request);

        entryServices.save(entry);

    }

    private static Entry createEntryFromRequest(CreateEntryRequest request) {
        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setUsername(request.getUsername().toLowerCase());
        return entry;
    }


    @Override
    public void updateEntryWith(UpdateEntryRequest request) {
        Diary myDiary = findDiaryBy(request.getUserName().toLowerCase());

//        if (myDiary == null) {
//            throw new IllegalArgumentException("Diary does not exist.");
//        }
        if (myDiary.isLocked()) {
            throw new IllegalStateException("Diary is locked. You need to login to use this service.");
        }

        Entry entry = createEntryFromRequest(request);

        entryServices.save(entry);


    }

    private static Entry createEntryFromRequest(UpdateEntryRequest request) {
        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setUsername(request.getUserName().toLowerCase());
        entry.setEntry_Id(request.getId());
        return entry;
    }

    @Override
    public void deleteEntryBy(int id, String username) {
        Diary myDiary = findDiaryBy(username.toLowerCase());
        if (myDiary.isLocked()) {
            throw new IllegalStateException("Diary is locked. You need to login to use this service.");
        }
//        List<Entry> entries = myDiary.getEntries();
//        if (entries.isEmpty()) {
//            throw new IllegalArgumentException("No entries found.");
//        }

        entryServices.deleteEntryBy(id);
//        myDiary.setEntries(entries);


    }

    private boolean validateRegistration(RegisterRequest registerRequest) {
        return validateUsername(registerRequest.getUsername()) &&
                validatePassword(registerRequest.getPassword()) &&
                validateExistingUsername(registerRequest.getUsername());
    }

    private boolean validateLogin(LoginRequest loginRequest) {
        Diary user = myRepository.findById(loginRequest.getUsername());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return validateUsername(loginRequest.getUsername()) &&
                validatePassword(loginRequest.getPassword());
    }


    private boolean validateUsername(String username) {
        return username != null && !username.isEmpty();
    }

    private boolean validatePassword(String password) {
        return password != null && !password.isEmpty();
    }

    private boolean validateExistingUsername(String username) {
        return myRepository.findById(username) == null;
    }

    public boolean isLoggedIn() {
        return loginUser;
    }
}
