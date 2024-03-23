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
    //        @Autowired
    public final DiaryRepositories myRepository;
    //        @Autowired

    //        public DiaryServiceImplementation(DiaryRepositories myRepository, EntryServices entryServices) {
//            this.myRepository = myRepository;
//            this.entryServices = entryServices;
//        }
    @Override
    public void registerUser(RegisterRequest registerRequest) {
//        if (validateRegistration(registerRequest)) {
        Diary user = new Diary();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());

        myRepository.save(user);
//        } else {
//            throw new IllegalArgumentException("Registration failed");
//        }
    }


    @Override
    public void login(LoginRequest loginRequest) {
        boolean isValidLogin = validateLogin(loginRequest);
        if (isValidLogin) {
            loginUser = true;
        }
    }

    @Override
    public long getNumberOfUsers() {
        return myRepository.count();
    }

    @Override
    public Diary findDiaryBy(String username) {
        Optional<Diary> foundDiary = myRepository.findByUsername(username.toLowerCase());
        if (foundDiary.isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        return foundDiary.get();

    }

    @Override
    public void logout(String username) {
        Optional<Diary> foundDiary = myRepository.findById(username.toLowerCase());
        if (foundDiary.isPresent()) {
            loginUser = false;
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
//        myRepository.delete(myDiary);

    }


    private static boolean isPasswordIncorrect(Diary foundDiary, String password) {
        return !foundDiary.getPassword().equals(password);
    }


//    @Override
//    public void createEntryWith(CreateEntryRequest request) {
//        Diary myDiary = findDiaryBy(request.getUsername().toLowerCase());
//
////        if (myDiary == null) {
////            throw new IllegalArgumentException("Diary does not exist.");
////        }
//
//        if (myDiary.isLock()) {
//            throw new IllegalStateException("Diary is locked. You need to login to use this service.");
//        }
//
//        Entry entry = createEntryFromRequest(request);
//
//        entryServices.save(entry);
//
//    }

//    private static Entry createEntryFromRequest(CreateEntryRequest request) {
//        Entry entry = new Entry();
//        entry.setTitle(request.getTitle());
//        entry.setBody(request.getBody());
//        entry.setUsername(request.getUsername().toLowerCase());
//        return entry;
//    }


//    @Override
//    public void updateEntryWith(UpdateEntryRequest request) {
//        Diary myDiary = findDiaryBy(request.getUserName().toLowerCase());
//
////        if (myDiary == null) {
////            throw new IllegalArgumentException("Diary does not exist.");
////        }
//        if (myDiary.isLock()) {
//            throw new IllegalStateException("Diary is locked. You need to login to use this service.");
//        }
//
//        Entry entry = createEntryFromRequest(request);
//
//        entryServices.save(entry);
//
//
//    }

//    private static Entry createEntryFromRequest(UpdateEntryRequest request) {
//        Entry entry = new Entry();
//        entry.setTitle(request.getTitle());
//        entry.setBody(request.getBody());
//        entry.setUsername(request.getUserName().toLowerCase());
//        entry.setEntry_Id(request.getId());
//        return entry;
//    }

//    @Override
//    public void deleteEntryBy(int id, String username) {
//        Diary myDiary = findDiaryBy(username.toLowerCase());
//        if (myDiary.isLock()) {
//            throw new IllegalStateException("Diary is locked. You need to login to use this service.");
//        }
////        List<Entry> entries = myDiary.getEntries();
////        if (entries.isEmpty()) {
////            throw new IllegalArgumentException("No entries found.");
////        }
//
//        entryServices.deleteEntryBy(id);
////        myDiary.setEntries(entries);
//
//
//    }

    private boolean validateRegistration(RegisterRequest registerRequest) {
        return validateUsername(registerRequest.getUsername()) &&
                validatePassword(registerRequest.getPassword()) &&
                validateExistingUsername(registerRequest.getUsername());
    }

    private boolean validateLogin(LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new IllegalArgumentException("Invalid login request");
        }

        Diary user = myRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalStateException("Username or password is incorrect.");
        }
        return true;
    }

    private boolean validateUsername(String username) {
        return username != null && !username.isEmpty();
    }

    private boolean validatePassword(String password) {
        return password != null && !password.isEmpty();
    }

    private boolean validateExistingUsername(String username) {
        return myRepository.findById(username).isEmpty();
    }

    public boolean userExists(String username) {
        return myRepository.findByUsername(username).isPresent();
    }

    @Override
    public List<Diary> getAllDiaries() {
        System.out.println(myRepository.findAll().size() + " ==============");
        return myRepository.findAll();
    }

    public boolean isLoggedIn() {
        return loginUser;
    }
}
