package ee.ivkhkdev.helpers;

import ee.ivkhkdev.App;
import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Company;
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


class AppHelperCompanyTest {
    Input inputMock;
    AppHelper<Company> companyAppHelper;
    PrintStream defaultOut = System.out;
    ByteArrayOutputStream outMock;

    @BeforeEach
    void setUp() {
        inputMock = Mockito.mock(Input.class);
        companyAppHelper = new AppHelperCompany(inputMock);
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
        when(inputMock.nextLine()).thenReturn("ASUS");
        Company actual = companyAppHelper.create();
        Company expected = new Company("ASUS");
        assertEquals(actual.getName(), expected.getName());
    }

    @Test
    void printList() {
        Company author = new Company("ASUS");
        List<Company> authors = new ArrayList<>();
        authors.add(author);
        boolean result = companyAppHelper.printList(authors);
        boolean expected = true;
        assertTrue(result);
        String expectedString = "1. ASUS";
        assertTrue(outMock.toString().contains(expectedString));

    }
}