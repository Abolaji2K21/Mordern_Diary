package africa.semicolon.Mordern_Diary.services;

import africa.semicolon.Mordern_Diary.data.model.Diary;
import africa.semicolon.Mordern_Diary.data.repositories.DiaryRepositories;
import africa.semicolon.Mordern_Diary.dtos.requests.LoginRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.RemoveUserRequest;
import africa.semicolon.Mordern_Diary.dtos.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryServiceImplementation implements DiaryService {
    private boolean loginUser = false;
    public final DiaryRepositories myRepository;

    @Override
    public void registerUser(RegisterRequest registerRequest) {
            String username = registerRequest.getUsername();

        Optional<Diary> existingDiary = myRepository.findByUsername(username);
        if (existingDiary.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

            Diary user = new Diary();
            user.setUsername(username);
            user.setPassword(registerRequest.getPassword());

            myRepository.save(user);
        }

    @Override
    public void login(LoginRequest loginRequest) {
        if (validateLogin(loginRequest)) {
            setLoginUser(true);
        }
    }

    @Override
    public long getNumberOfUsers() {
        return myRepository.count();
    }

    @Override
    public Diary findDiaryBy(String username) {
        username = username.toLowerCase();
        Optional<Diary> foundDiary = myRepository.findByUsername(username);
        if (foundDiary.isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        return foundDiary.get();
    }


    @Override
    public void logout(String username) {
        Optional<Diary> foundDiary = myRepository.findById(username.toLowerCase());
        if (foundDiary.isPresent()) {
            setLoginUser(false);
        }
    }

    @Override
    public void removeUser(RemoveUserRequest request) {
        Diary myDiary = findDiaryBy(request.getUsername().toLowerCase());
        if (myDiary.isLock()) throw new IllegalStateException("You need to login to use this service.");

        if (isPasswordIncorrect(myDiary, request.getPassword()))
            throw new IllegalArgumentException("Password is incorrect.");
        myDiary.setDeleted(true);
        myRepository.save(myDiary);
    }

    private static boolean isPasswordIncorrect(Diary foundDiary, String password) {
        return !foundDiary.getPassword().equals(password);
    }

    private boolean validateLogin(LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new IllegalArgumentException("Invalid login request");
        }

        Optional<Diary> foundDiary = myRepository.findByUsername(loginRequest.getUsername());
        if (foundDiary.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Diary user = foundDiary.get();
        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Username or password is incorrect.");
        }

        return true;
    }

    @Override
    public List<Diary> getAllDiaries() {
        return myRepository.findAll();
    }

    public boolean isLoggedIn() {
        return loginUser;
    }

    private void setLoginUser(boolean loggedIn) {
        this.loginUser = loggedIn;
    }
}
