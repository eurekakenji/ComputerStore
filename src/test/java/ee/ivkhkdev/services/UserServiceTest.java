package ee.ivkhkdev.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class UserServiceTest {

    private UserService userService;
    private Repository<User> mockRepository;
    private AppHelper<User> mockAppHelperUser;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(Repository.class);
        mockAppHelperUser = Mockito.mock(AppHelper.class);
        userService = new UserService(mockAppHelperUser, mockRepository);
    }

    @Test
    void testAddUserSuccess() {
        User mockUser = new User();
        when(mockAppHelperUser.create()).thenReturn(mockUser);
        boolean result = userService.add();
        assertTrue(result);
        verify(mockRepository,times(1)).save(mockUser);
    }

    @Test
    void testAddUserFailureWhenUserIsNull() {
        when(mockAppHelperUser.create()).thenReturn(null);
        boolean result = userService.add();
        assertFalse(result);
        verify(mockRepository, never()).save(any());
    }

    @Test
    void testAddUserExceptionHandling() {
        User mockUser = new User();
        when(mockAppHelperUser.create()).thenReturn(mockUser);
        doThrow(new RuntimeException("Save error")).when(mockRepository).save(mockUser);
        boolean result = userService.add();
        assertFalse(result);
    }

    @Test
    void testPrint() {
        List<User> mockUserList = List.of(new User(), new User());
        when(mockRepository.load()).thenReturn(mockUserList);
        when(mockAppHelperUser.printList(mockUserList)).thenReturn(true);
        boolean result = userService.print();
        assertTrue(result);
        verify(mockRepository, times(1)).load();
        verify(mockAppHelperUser, times(1)).printList(mockUserList);
    }

    @Test
    void testList() {
        List<User> mockUserList = List.of(new User(), new User());
        when(mockRepository.load()).thenReturn(mockUserList);
        List<User> result = userService.list();
        assertEquals(mockUserList, result);
        verify(mockRepository, times(1)).load();
    }
}