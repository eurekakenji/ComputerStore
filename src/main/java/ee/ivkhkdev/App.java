package ee.ivkhkdev;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.model.PurchaseHistory;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Service;

public class App {
    private final Service<PurchaseHistory> purchaseHistoryService;
    private Input input;
    private Service<User> userService;
    private Service<Computer> computerService;
    private Service<Company> companyService;



    public App(Input input, Service<Computer> computerService, Service<User> userService, Service<Company> companyService, Service<PurchaseHistory> purchaseHistoryService) {
        this.input = input;
        this.computerService = computerService;
        this.userService = userService;
        this.companyService = companyService;
        this.purchaseHistoryService = purchaseHistoryService;

    }


    public void run() {
        boolean repeat = true;
        System.out.println("======= Computer Store =========");
        do {
            System.out.println("List of tasks:");
            System.out.println("0. Exit program");
            System.out.println("1. Add user");
            System.out.println("2. List of users");
            System.out.println("3. Edit user");
            System.out.println("4. Add computer");
            System.out.println("5. List of computers");
            System.out.println("6. Edit computer");
            System.out.println("7. Add company");
            System.out.println("8. Buy computer");
            System.out.println("9. Purchase history");
            System.out.print("Enter task number: ");
            int task = Integer.parseInt(input.nextLine());
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
                    System.out.println("Editing user");
                    if(userService.edit()){
                        System.out.println("User edited");
                    }else {
                        System.out.println("Failed to edit user");
                    }
                    break;
                case 4:
                    System.out.println("Adding computer");
                    if(computerService.add()){
                        System.out.println("Computer added");
                    }else {
                        System.out.println("Failed to add computer");
                    }
                    break;
                case 5:
                    if(computerService.print()){
                        System.out.println("----------- end of list -----------");
                    }
                    break;
                case 6:
                    System.out.println("Editing computer");
                    if(userService.edit()){
                        System.out.println("Computer edited");
                    }else {
                        System.out.println("Failed to edit computer");
                    }
                    break;

                case 7:
                    System.out.println("Adding company");
                    if(companyService.add()){
                        System.out.println("Added company");
                    }else{
                        System.out.println("Failed to add company");
                    }
                    break;

                case 8:
                    System.out.println("Buy computer");
                    if(purchaseHistoryService.add()){
                        System.out.println("Computer bought");
                    }else{
                        System.out.println("Unable to confirm purchase");
                    }
                    break;
                case 9:
                    if(purchaseHistoryService.print()){
                        System.out.println("----------- end of list -----------");
                    }
                    break;
                default:
                    System.out.println("Pick a number from the list!");
                    break;
            }

            System.out.println("==============================");
        } while (repeat);
        System.out.println("Goodbye! :3");
    }
}
