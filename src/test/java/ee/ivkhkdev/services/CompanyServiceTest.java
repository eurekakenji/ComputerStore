package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.helpers.AppHelperCompany;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.repository.Repository;
import ee.ivkhkdev.repository.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyServiceTest {
    List<Company> companies;
    Service<Company> companyService;
    Repository<Company> repositoryMock;
    AppHelper<Company> appHelperCompanyMock;



    @BeforeEach
    void setUp() {
        Company company = new Company("ASUS");
        companies = new ArrayList<>();
        companies.add(company);
        appHelperCompanyMock = Mockito.mock(AppHelperCompany.class);
        when(appHelperCompanyMock.create()).thenReturn(new Company("Acer"));
        repositoryMock = Mockito.mock(Storage.class);
        companyService = new CompanyService(companies, appHelperCompanyMock, repositoryMock);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAdd_SuccessfulAdd() {
        Company mockCompany = new Company("Acer");
        when(appHelperCompanyMock.create()).thenReturn(mockCompany);
        boolean result = companyService.add();
        assertTrue(result);
        assertTrue(companies.get(1).getName().equals("Acer"));
        assertEquals(mockCompany,companies.get(0));
        verify(repositoryMock,times(1)).save(any(Company.class));
    }


    @Test
    void restAdd_CreateReturnNull() {
        companies = new ArrayList<>();
        when(appHelperCompanyMock.create()).thenReturn(null);
        boolean result = companyService.add();
        assertFalse(result);
        assertTrue(companies.isEmpty());
        verify(repositoryMock, never()).save((any()));

    }


    @Test
    void testAdd_AddExistingCompany(){
        Company existingCompany = new Company();
        companies = new ArrayList<>();
        companies.add(existingCompany);
        Company newCompany = new Company();
        when(appHelperCompanyMock.create()).thenReturn(new Company());
        boolean result = companyService.add();
        assertTrue(result);
        assertNotEquals(2, companies.size());
        assertEquals(newCompany, companies.get(1));
        verify(repositoryMock, times(1)).save(newCompany);
    }

    @Test
    void testPrint() {
        when(appHelperCompanyMock.printList(companies)).thenReturn(true);
        boolean result = companyService.print();
        assertTrue(result);
        verify(appHelperCompanyMock, times(1)).printList(companies);
    }

    @Test
    void testList() {
    }
}