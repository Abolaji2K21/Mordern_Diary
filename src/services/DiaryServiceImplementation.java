package services;

import data.model.Diary;
import data.model.Entry;
import data.repositories.DiaryRepositories;
import dtos.requests.*;

public class DiaryServiceImplementation implements DiaryService {
    private final DiaryRepositories myRepository;

    public DiaryServiceImplementation(DiaryRepositories myRepository) {
        this.myRepository = myRepository;
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
    public void removeUser(Diary myDiary,RemoveUserRequest request) {
        Diary foundDiary = findDiaryBy(request.getUsername().toLowerCase());
        if (myDiary.isLocked()) throw new IllegalStateException("You need to login to use this service.");

        if (isPasswordIncorrect(foundDiary, request.getPassword())) throw new IllegalArgumentException("Password is incorrect.");

        myRepository.delete(foundDiary);

    }


    private static boolean isPasswordIncorrect(Diary foundDiary, String password) {
        return !foundDiary.getPassword().equals(password);
    }


    @Override
    public void createEntryWith(Diary myDiary,CreateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getUsername().toLowerCase());
        if (myDiary.isLocked()) throw new IllegalStateException("You need to login to use this service.");

        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setUsername(request.getUsername().toLowerCase());

        entryServices.save(entry);

    }

    @Override
    public void updateEntryWith(UpdateEntryRequest request) {

    }

    @Override
    public void deleteEntryBy(int id, String username) {

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
