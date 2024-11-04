package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.model.PurchaseHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PurchaseHistoryServiceTest {

    private PurchaseHistoryService purchaseHistoryService;
    private AppHelper<PurchaseHistory> mockPurchaseHistoryAppHelper;
    private Repository<PurchaseHistory> mockRepository;

    @BeforeEach
    void setUp() {
        mockPurchaseHistoryAppHelper = Mockito.mock(AppHelper.class);
        mockRepository = Mockito.mock(Repository.class);

        purchaseHistoryService = new PurchaseHistoryService(mockPurchaseHistoryAppHelper, mockRepository);
    }

    @Test
    void testAddPurchaseHistorySuccess() {
        PurchaseHistory mockPurchaseHistory = new PurchaseHistory();
        when(mockPurchaseHistoryAppHelper.create()).thenReturn(mockPurchaseHistory);

        boolean result = purchaseHistoryService.add();

        assertTrue(result);
        verify(mockRepository, times(1)).save(mockPurchaseHistory);
    }

    @Test
    void testAddPurchaseHistoryFailureWhenPurchaseHistoryIsNull() {
        when(mockPurchaseHistoryAppHelper.create()).thenReturn(null);

        boolean result = purchaseHistoryService.add();

        assertFalse(result);
        verify(mockRepository, never()).save(any());
    }

    @Test
    void testAddPurchaseHistoryExceptionHandling() {
        PurchaseHistory mockPurchaseHistory = new PurchaseHistory();
        when(mockPurchaseHistoryAppHelper.create()).thenReturn(mockPurchaseHistory);
        doThrow(new RuntimeException("Save error")).when(mockRepository).save(mockPurchaseHistory);

        boolean result = purchaseHistoryService.add();

        assertFalse(result);
    }

    @Test
    void testPrint() {
        List<PurchaseHistory> mockLibraryCardList = List.of(new PurchaseHistory());
        when(mockRepository.load()).thenReturn(mockLibraryCardList);
        when(mockPurchaseHistoryAppHelper.printList(mockLibraryCardList)).thenReturn(true);

        boolean result = purchaseHistoryService.print();

        assertTrue(result);
        verify(mockRepository, times(1)).load();
        verify(mockPurchaseHistoryAppHelper, times(1)).printList(mockLibraryCardList);
    }

    @Test
    void testList() {
        List<PurchaseHistory> mockLibraryCardList = List.of(new PurchaseHistory());
        when(mockRepository.load()).thenReturn(mockLibraryCardList);

        List<PurchaseHistory> result = purchaseHistoryService.list();

        assertEquals(mockLibraryCardList, result);
        verify(mockRepository, times(1)).load();
    }
}