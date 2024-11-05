package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.model.PurchaseHistory;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.ComputerService;
import ee.ivkhkdev.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PurchaseHistoryAppHelperTest {
    private PurchaseHistoryAppHelper purchaseHistoryAppHelper;
    private Input mockInput;
    private Service<Computer> mockComputerService;
    private Service<User> mockUserService;


    @BeforeEach
    void setUp() {
        mockInput = Mockito.mock(Input.class);
        mockComputerService = Mockito.mock(ComputerService.class);
        mockUserService = Mockito.mock(UserService.class);

        purchaseHistoryAppHelper = new PurchaseHistoryAppHelper(mockInput, mockComputerService, mockUserService);
    }

    @AfterEach
    void testCreateSuccess() {
        Computer mockComputer = new Computer();
        User mockUser = new User();
        when(mockComputerService.print()).thenReturn(true);
        when(mockComputerService.list()).thenReturn(List.of(mockComputer));
        when(mockInput.nextLine()).thenReturn("1");
        when(mockUserService.print()).thenReturn(true);
        when(mockUserService.list()).thenReturn(List.of(mockUser));
        when(mockInput.nextLine()).thenReturn("1");

        PurchaseHistory result = purchaseHistoryAppHelper.create();

        assertNotNull(result);
        assertEquals(mockComputer, result.getComputer());
        assertEquals(mockUser, result.getUser());
        assertEquals(LocalDate.now(), result.getPurchases());
    }

    @Test
    void testComputerServicePrintFails() {
        when(mockComputerService.print()).thenReturn(false);

        PurchaseHistory result = purchaseHistoryAppHelper.create();

        assertNull(result);
    }

    @Test
    void testCreateUserServicePrintFails() {
        Computer mockComputer = new Computer();
        when(mockComputerService.print()).thenReturn(true);
        when(mockComputerService.list()).thenReturn(List.of(mockComputer));
        when(mockInput.nextLine()).thenReturn("1");
        when(mockUserService.print()).thenReturn(false);

        PurchaseHistory result = purchaseHistoryAppHelper.create();

        assertNull(result);
    }

    @Test
    void testPrintListOfPurchasesTrue() {
        Computer computer = new Computer("Test computer",List.of(new Company("Test company")), 2020);
        User user = new User("Karl", "Wheezer","12345678");
        PurchaseHistory purchaseHistory = new PurchaseHistory(computer, user, null);
        List<PurchaseHistory> purchaseHistories = List.of(purchaseHistory);

        boolean result = purchaseHistoryAppHelper.printList(purchaseHistories);

        assertTrue(result);
    }

    @Test
    void testPrintListOfPurchasesFalse() {
        Computer computer = new Computer("Test computer", List.of(new Company("Test company")),2024);
        User user = new User("Karl", "Wheezer", "12345678");
        PurchaseHistory purchaseHistory = new PurchaseHistory(computer, user, LocalDate.now());
        List<PurchaseHistory> purchaseHistoryList = List.of(purchaseHistory);

        boolean result = purchaseHistoryAppHelper.printList(purchaseHistoryList);

        assertFalse(result);
    }

    @Test
    void testReturnBackWithValidSelection() {
        Computer computer = new Computer("Test computer",List.of(new Company("Test company")),2024);
        User user = new User("Karl", "Wheezer", "12345678");
        PurchaseHistory purchaseHistory = new PurchaseHistory(computer, user, LocalDate.now());
        List<PurchaseHistory> purchaseHistoryList = List.of(purchaseHistory);

        when(mockInput.nextLine()).thenReturn("1");

        List<PurchaseHistory> result = purchaseHistoryAppHelper.returnBack(purchaseHistoryList);

        assertNotNull(result);
    }

}