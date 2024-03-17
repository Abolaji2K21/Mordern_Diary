package services;

import data.model.Diary;
import data.repositories.DiaryRepositories;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;

public class DiaryServiceImplementation implements DiaryService {
    private final DiaryRepositories myRepository;

    public DiaryServiceImplementation(DiaryRepositories myRepository) {
        this.myRepository = myRepository;
    }

    private Diary loggedInUser;

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        if (validateRegistration(registerRequest)) {
            Diary user = new Diary();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(registerRequest.getPassword());

            myRepository.save(user);
        } else {
            throw new IllegalArgumentException("Registration validation failed");
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
