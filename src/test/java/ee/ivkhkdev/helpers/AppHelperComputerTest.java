package ee.ivkhkdev.helpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.services.Service;
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

class AppHelperComputerTest {
    Input inputMock;
    AppHelper<Computer> appHelperComputer;
    Service<Company> companyServiceMock;
    PrintStream defaultOut = System.out;
    ByteArrayOutputStream outMock;

    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        companyServiceMock = Mockito.mock(Service.class);
        appHelperComputer = new AppHelperComputer(inputMock, companyServiceMock);
        outMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outMock));

    }

    @AfterEach
    void tearDown() {
        inputMock = null;
        companyServiceMock = null;
        appHelperComputer = null;
        System.setOut(defaultOut);
        outMock = null;
    }

    @Test
    void createWithAddCompany() {
        when(inputMock.nextLine()).thenReturn("TUF Gaming A15","y");
        Computer result = appHelperComputer.create();
        Computer expected = null;
        assertEquals(result, null);
    }

    @Test
    void createWithoutAddCompany() {
        Company company = new Company("ASUS");
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        when(companyServiceMock.list()).thenReturn(companies);
        when(inputMock.nextLine()).thenReturn("TUF Gaming A15","n","2023");
        Computer result = appHelperComputer.create();
        Computer expected = new Computer("TUF Gaming A15", companies, 2023);
        assertEquals(result.getModel(), expected.getModel());
        assertEquals(result.getCompanies().get(0).getName(), expected.getCompanies().get(0).getName());
        assertEquals(result.getReleaseYear(), expected.getReleaseYear());

    }

    @Test
    void printList() {
        Company company = new Company("ASUS");
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        Computer expected = new Computer("TUF Gaming A15", companies, 2023);
        List<Computer>computers= new ArrayList<>();
        computers.add(expected);
        appHelperComputer.printList(computers);
        String out = outMock.toString();
        String expect = "TUF Gaming A15. ASUS. 2023";
        assertTrue(out.contains(expect));

    }
}