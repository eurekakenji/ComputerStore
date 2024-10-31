package ee.ivkhkdev.helpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AppHelperUserTest {
    Input inputMock;
    AppHelper<User> appHelperUser;
    PrintStream defaultOut = System.out;
    ByteArrayOutputStream outMock;

    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        appHelperUser = new AppHelperUser(inputMock);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));
    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        System.setOut(defaultOut);
        outMock = null;
    }

    @Test
    void create() {
        when(inputMock.nextLine()).thenReturn("Karl","Wheezer", "12345678");
        User actual = appHelperUser.create();
        User expected = new User("Karl","Wheezer", "12345678");
        assertEquals(actual.getFirstName(), expected.getFirstName());
        assertEquals(actual.getLastName(), expected.getLastName());
    }

    @Test
    void printList() {
        User user = new User("Karl","Wheezer", "12345678");
        List<User> users = new ArrayList<>();
        users.add(user);
        boolean result = appHelperUser.printList(users);
        boolean expected = true;
        assertTrue(result);
        String expectedString = "1. Karl Wheezer. 12345678";
        assertTrue(outMock.toString().contains(expectedString));
    }
}