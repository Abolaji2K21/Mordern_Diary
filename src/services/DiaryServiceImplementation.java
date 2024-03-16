package services;

import data.model.Diary;
import data.repositories.DiaryRepositories;
import data.repositories.DiaryRepositoriesImplement;
import dtos.requests.RegisterRequest;


public class DiaryServiceImplementation implements DiaryService {
    private final DiaryRepositories myRepository = new DiaryRepositoriesImplement();

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        validateRegistration(registerRequest);
        Diary diary = new Diary();
        diary.setUsername(registerRequest.getUsername());
        diary.setPassword(registerRequest.getPassword());

        myRepository.save(diary);
        }

    @Override
        public void login(String username, String password) {
        }

    @Override
      public long getNumberOfUsers() {
        return myRepository.count();
        }


    private void validateRegistration(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        validate(username);
        check(password);
        validateExisting(username);
    }


    private void validateExisting(String username) {
        if (myRepository.findById(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    private static void check(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty or null");
        }
    }

    private static void validate(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty or null");
        }
    }
}

