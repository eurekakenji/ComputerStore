package ee.ivkhkdev;

import ee.ivkhkdev.helpers.*;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Repository;
import ee.ivkhkdev.services.CompanyService;
import ee.ivkhkdev.services.ComputerService;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.services.PurchaseHistoryService;
import ee.ivkhkdev.services.UserService;
import ee.ivkhkdev.repository.Storage;
import java.util.List;
import java.util.Scanner;

public class App {
    private Input input;
    public List<User> users;
    public List<Computer> computers;
    public List<Company> companies;

    private AppHelper appHelperCompany;
    private AppHelper appHelperComputer;
    private AppHelper appHelperUser;
    private Repository<Company> companyRepository;
    private Repository<User> userRepository;
    private Repository<Computer> computerRepository;
    private Service <User> userService;
    private Service <Computer> computerService;
    private Service <Company> companyService;


    public App() {
        userRepository = new Storage<User>("users");
        computerRepository = new Storage<Computer>("computers");
        companyRepository = new Storage<Company>("companies");

        this.users = this.userRepository.load();
        this.companies = this.companyRepository.load();
        this.computers = this.computerRepository.load();
        this.input = new ConsoleInput(new Scanner(System.in));

        appHelperUser = new UserAppHelper(input);
        appHelperCompany = new CompanyAppHelper(input);

        userService = new UserService(users,appHelperUser,userRepository);
        companyService = new CompanyService(companies,appHelperCompany,companyRepository);
        appHelperComputer = new ComputerAppHelper(input,companyService);
        computerService = new ComputerService(computers,appHelperComputer,computerRepository);
    }

    public void run() {
        boolean repeat = true;
        System.out.println("======= Computer Store =========");
        do {
            System.out.println("List of tasks:");
            System.out.println("0. Exit program");
            System.out.println("1. Add user");
            System.out.println("2. List of users");
            System.out.println("3. Add computer");
            System.out.println("4. List of computers");
            System.out.println("5. Add company");
            System.out.println("6. Buy computer");
            System.out.println("7. Purchase history");
            System.out.print("Enter task number: ");
            int task = Integer.parseInt(input.nextLine()); // Используем input
            switch (task) {
                case 0:
                    System.out.println("exiting program...");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("Adding user");
                    if(userService.add()){
                        System.out.println("User added");
                    }else{
                        System.out.println("Failed to add user");
                    };
                    break;
                case 2:
                    if(userService.print()){
                        System.out.println("----------- end of list -----------");
                    }
                    break;
                case 3:
                    System.out.println("Adding computer");
                    if(computerService.add()){
                        System.out.println("Computer added");
                    }else {
                        System.out.println("Failed to add computer");
                    }
                    break;
                case 4:
                    if(computerService.print()){
                        System.out.println("----------- end of list -----------");
                    }
                    break;
                case 5:
                    System.out.println("Adding company");
                    if(companyService.add()){
                        System.out.println("Added company");
                    }else{
                        System.out.println("Failed to add company");
                    };
                    break;
                default:
                    System.out.println("Pick a number from the list!");
                    break;

                case 6:
                    System.out.println("Buy computer");
                    if(PurchaseHistoryService.add()){
                        System.out.println("Computer bought");
                    }else{
                        System.out.println("Unble to confirm purchase");
                    };
                    break;


            }
            System.out.println("==============================");
        } while (repeat);
        System.out.println("Goodbye! :3");
    }
}
