package services;

import data.model.Diary;
import data.repositories.DiaryRepositories;
import data.repositories.DiaryRepositoriesImplement;
import data.repositories.EntryRepositories;
import data.repositories.EntryRepositoryImplement;
import dtos.requests.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImplementationTest {

        private DiaryServiceImplementation diaryService;
        private DiaryRepositories myRepository;
        private EntryRepositories entryRepositories;
        private EntryServices entryServices;

        @BeforeEach
        public void initializationStep() {
            myRepository = new DiaryRepositoriesImplement();
            entryRepositories = new EntryRepositoryImplement();
            entryServices = new EntryServicesImplementation();
            diaryService = new DiaryServiceImplementation(myRepository, entryServices);
        }

    @Test
    public void testRegisterUser_Success() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("testUser");
        registerRequest.setPassword("password");
        diaryService.registerUser(registerRequest);
        assertEquals(1L, diaryService.getNumberOfUsers());
    }

    @Test
    public void testRegisterUser_ViaEmptyUsername_Failure() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("");
        registerRequest.setPassword("password");

        assertThrows(IllegalArgumentException.class, () -> diaryService.registerUser(registerRequest));
    }

    @Test
    public void testRegisterUser_ViaNullUsername_Failure() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(null);
        registerRequest.setPassword("password");
        assertThrows(IllegalArgumentException.class, () -> diaryService.registerUser(registerRequest));
    }

    @Test
    public void testRegisterUser_ViaEmptyPassword_Failure() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Holes");
        registerRequest.setPassword("");
        assertThrows(IllegalArgumentException.class, () -> diaryService.registerUser(registerRequest));
    }


    @Test
    public void testRegisterUser_ViaNullPassword_Failure() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Holes");
        registerRequest.setPassword(null);
        assertThrows(IllegalArgumentException.class, () -> diaryService.registerUser(registerRequest));
    }

    @Test
    public void testRegisterUserUsingCaseSensitiveLetter_Success() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("hOles");
        registerRequest.setPassword("password");
        diaryService.registerUser(registerRequest);
        assertEquals(1L, diaryService.getNumberOfUsers());
    }

    @Test
    public void testRegisterUser_TwoUsersCanNotRegisterWithTheSameUserName() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("hOles");
        registerRequest.setPassword("password");
        diaryService.registerUser(registerRequest);
        assertEquals(1L, myRepository.count());
        registerRequest.setUsername("HOles");
        registerRequest.setPassword("password");
        assertThrows(IllegalArgumentException.class, () -> diaryService.registerUser(registerRequest));
        assertEquals(1L, diaryService.getNumberOfUsers());
    }

    @Test
    void testLoginRequestOfARegisterUser() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("hOles");
        registerRequest.setPassword("password");
        diaryService.registerUser(registerRequest);
        assertEquals(1L, diaryService.getNumberOfUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("hOles");
        loginRequest.setPassword("password");
//        boolean loginResult = diaryService.login(loginRequest);
        assertTrue(diaryService.login(loginRequest));
    }

//    @Test
//    void testLoginRequestOfARegisterUserWithWrong_Failure() {
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setUsername("hOles");
//        registerRequest.setPassword("password");
//        diaryService.registerUser(registerRequest);
//        assertEquals(1L, diaryService.getNumberOfUsers());
//
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername("hOles");
//        loginRequest.setPassword("password@");
////        boolean loginResult = diaryService.login(loginRequest);
//        assertFalse(diaryService.login(loginRequest));
//    }

    @Test
    public void loginUserWithNonExistingUsername_ThrowsExceptionTest() {
        LoginRequest login = new LoginRequest();
        login.setUsername("Holes");
        login.setPassword("password");
        assertThrows(IllegalArgumentException.class, () -> diaryService.login(login));
    }


    @Test
    public void loginUserWithIncorrectPassword_ThrowsException() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Holes");
        request.setPassword("password");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("Holes");
        login.setPassword("password@");
        assertThrows(IllegalArgumentException.class, () -> diaryService.login(login));
    }

    @Test
    public void loginUserWithNullPassword_ThrowsException() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Holes");
        request.setPassword("password");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("Holes");
        login.setPassword(null);
        assertThrows(IllegalArgumentException.class, () -> diaryService.login(login));
    }

    @Test
    public void loginUserWithEmptyPassword_ThrowsException() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Holes");
        request.setPassword("password");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("Holes");
        login.setPassword("");
        assertThrows(IllegalArgumentException.class, () -> diaryService.login(login));
    }


    @Test
    public void loginUserWithValidLoginsAndThenLogOut_Success() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("PenIsUp");
        request.setPassword("Satisfied");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("PenIsUp");
        login.setPassword("Satisfied");
        diaryService.login(login);
        assertTrue(diaryService.isLoggedIn());

        LogoutRequest logout = new LogoutRequest();
//        logout.setUsername("PenIsUp");
        diaryService.logout("PenIsUp");
        assertFalse(diaryService.isLoggedIn());

    }

    @Test
    public void loginUserWithValidLoginsAndThenLogOutWithAnInvalidUserName_Failure() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("PenIsUp");
        request.setPassword("Satisfied");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("PenIsUp");
        login.setPassword("Satisfied");
        diaryService.login(login);
        assertTrue(diaryService.isLoggedIn());

        LogoutRequest logout = new LogoutRequest();
//        logout.setUsername("PenIsDown");
        assertThrows(IllegalArgumentException.class, () -> diaryService.logout("PenIsDown"));
        assertTrue(diaryService.isLoggedIn());

    }

    @Test
    public void testLogoutWhenNotLoggedIn_Failure() {
        assertThrows(IllegalArgumentException.class, () -> diaryService.logout("PenIsUp"));
    }

    @Test
    public void testCreateEntryWithValidRequest_Success() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("PenIsUp");
        registerRequest.setPassword("Satisfied");
        diaryService.registerUser(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("PenIsUp");
        loginRequest.setPassword("Satisfied");
        diaryService.login(loginRequest);
        assertEquals(0, entryServices.getEntriesFor("PenIsUp").size());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUsername("PenIsUp");
        createEntryRequest.setTitle("New Entry Title");
        createEntryRequest.setBody("New Entry Body");

        diaryService.createEntryWith(createEntryRequest);
        assertEquals(1, entryServices.getEntriesFor("penisup").size());


    }

    @Test
    public void testUpdateEntryWithValidRequest_Success() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("PenIsUp");
        registerRequest.setPassword("Satisfied");
        diaryService.registerUser(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("PenIsUp");
        loginRequest.setPassword("Satisfied");
        diaryService.login(loginRequest);


        UpdateEntryRequest updateEntryRequest = new UpdateEntryRequest();
        updateEntryRequest.setUserName("PenIsUp");
        updateEntryRequest.setId(1);
        updateEntryRequest.setTitle("Worries That Comes With Pen Been Up");
        updateEntryRequest.setBody(" Pen Is Still Very Up As At 3:38Am");

        diaryService.updateEntryWith(updateEntryRequest);
        assertEquals(1, entryServices.getEntriesFor("PenIsUp").size());

    }

    @Test
    public void testDeleteEntryByValidIdAndUsername_Success() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("PenIsUp");
        registerRequest.setPassword("Satisfied");
        diaryService.registerUser(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("PenIsUp");
        loginRequest.setPassword("Satisfied");
        diaryService.login(loginRequest);

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setUsername("PenIsUp");
        createEntryRequest.setTitle("New Entry Title");
        createEntryRequest.setBody("New Entry Body");
        diaryService.createEntryWith(createEntryRequest);

        diaryService.deleteEntryBy(1, "PenIsUp");

        assertEquals(0, entryServices.getEntriesFor("PenIsUp").size());
    }

}