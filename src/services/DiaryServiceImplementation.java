package services;

import data.model.Diary;
import data.model.Entry;
import data.repositories.DiaryRepositories;
import data.repositories.EntryRepositories;
import dtos.requests.*;

public class DiaryServiceImplementation implements DiaryService {
    private DiaryRepositories myRepository;
    private EntryServices entryServices = new EntryServicesImplementation();

    public DiaryServiceImplementation(DiaryRepositories myRepository) {
        this.myRepository = myRepository;
//        this.myEntryRepositories = myEntryRepositories;
    }

//    private Diary loggedInUser;

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
        return validateLogin(loginRequest);
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
        foundDiary.setLock(true);
        myRepository.save(foundDiary);

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

        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setUsername(request.getUsername().toLowerCase());

        entryServices.save(entry);

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

        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setUsername(request.getUserName().toLowerCase());
        entry.setEntry_Id(request.getId());

        entryServices.save(entry);


    }

    @Override
    public void deleteEntryBy(int id, String username) {
        Diary myDiary = findDiaryBy(username.toLowerCase());
        if (myDiary.isLocked()) {
            throw new IllegalStateException("Diary is locked. You need to login to use this service.");
        }
        entryServices.deleteEntryBy(id);

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
}
