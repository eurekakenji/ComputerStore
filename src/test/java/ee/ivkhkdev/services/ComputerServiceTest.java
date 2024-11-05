package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.ComputerAppHelper;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.repository.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComputerServiceTest {
    AppHelper<Computer> appHelperComputer;
    Repository<Computer> repositry;
    Service<Computer> computerService;

    @BeforeEach
    void setUp() {
        appHelperComputer = Mockito.mock(ComputerAppHelper.class);
        repositry = Mockito.mock(Storage.class);
        computerService = new ComputerService(appHelperComputer, repositry);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAdd_SuccessfulAdd() {
        Computer mockComputer = new Computer("TUF Gaming A15", List.of(new Company("ASUS")),2023);
        when(appHelperComputer.create()).thenReturn(mockComputer);
        boolean result = computerService.add();
        assertTrue(result);
    }

    @Test
    public void testAdd_CreateReturnsNull() {
        when(appHelperComputer.create()).thenReturn(null);
        boolean result = computerService.add();
        List<Computer> resultListComputer = computerService.list();
        assertFalse(result);
        assertTrue(resultListComputer.isEmpty());
        verify(repositry,never()).save(any());
    }

    @Test
    public void testAdd_AddExistingComputer() {
       Computer mockComputer = new Computer("TUF Gaming A15", List.of(new Company("ASUS")),2023);
       when(appHelperComputer.create()).thenReturn(mockComputer);
       when(repositry.load()).thenReturn(List.of(new Computer("TUF Gaming A15", List.of(new Company("ASUS")),2023)));
       Computer existingComputer = new Computer("TUF Gaming A15", List.of(new Company("ASUS")),2023);
       boolean result = computerService.add();
       Computer resultComputer = computerService.list().get(0);
       assertTrue(result);
       assertEquals(existingComputer.getModel(), resultComputer.getModel());
    }

    @Test
    public void testPrint() {
        List<Computer> mockComputerList = List.of(new Computer(), new Computer());
        when(repositry.load()).thenReturn(mockComputerList);
        when(appHelperComputer.printList(mockComputerList)).thenReturn(true);
        boolean result = computerService.print();
        assertTrue(result);
        verify(repositry,times(1)).load();
        verify(appHelperComputer,times(1)).printList(mockComputerList);
    }

    @Test
    public void testList() {
        List<Computer> mockComputerList = List.of(new Computer(), new Computer());
        when(repositry.load()).thenReturn(mockComputerList);
        when(appHelperComputer.printList(mockComputerList)).thenReturn(true);
        List<Computer> result = computerService.list();
        assertEquals(mockComputerList, result);
        verify(repositry,times(1)).load();
    }
    @Test
    public void testEditSuccessful() {
        List<Computer> computersMock= List.of(new Computer(), new Computer());
        when(appHelperComputer.edit(computersMock)).thenReturn(computersMock);
        boolean result = computerService.edit();
        assertTrue(result);
    }

    @Test
    public void testEditUnsuccessful() {
        List<Computer> computersMock= List.of(new Computer(), new Computer());
        when(appHelperComputer.edit(computersMock)).thenReturn(null);
        boolean result = computerService.edit();
        assertFalse(result);
    }
}