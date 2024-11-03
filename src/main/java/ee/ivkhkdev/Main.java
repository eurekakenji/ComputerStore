package ee.ivkhkdev;

import ee.ivkhkdev.helpers.PurchaseHistoryAppHelper;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.helpers.CompanyAppHelper;
import ee.ivkhkdev.helpers.ComputerAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.model.PurchaseHistory;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.repository.Storage;
import ee.ivkhkdev.services.CompanyService;
import ee.ivkhkdev.services.ComputerService;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.services.PurchaseHistoryService;
import ee.ivkhkdev.services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repository<Company> companyRepository = new Storage<>("companies");
        Repository<User> userRepository = new Storage<>("users");
        Repository<Computer> computerRepository = new Storage<>("computers");
        Input input = new ConsoleInput(new Scanner(System.in));
        AppHelper<Company> companyAppHelper = new CompanyAppHelper(input);
        AppHelper<User> userAppHelper = new UserAppHelper(input);
        Service<Company> companyService = new CompanyService(companyAppHelper,companyRepository);;
        AppHelper<Computer> computerAppHelper = new ComputerAppHelper(input,companyService);
        Service<User> userService = new UserService(userAppHelper,userRepository);
        Service<Computer> computerService = new ComputerService(computerAppHelper,computerRepository);
        AppHelper<PurchaseHistory> purchaseHistoryAppHelper= new PurchaseHistoryAppHelper(input,computerService,userService);
        Repository<PurchaseHistory> purchaseHistoryRepository= new Storage<>("PurchaseHistory");
        Service<PurchaseHistory> purchaseHistoryService = new PurchaseHistoryService(purchaseHistoryAppHelper, purchaseHistoryRepository);

        App app = new App(input, computerService,userService,companyService,purchaseHistoryService);
        app.run();
    }
}