package ee.ivkhkdev.helpers;

import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.services.Service;

import java.util.List;

public class AppHelperComputer implements AppHelper<Computer> {

    private final Input input;
    private final Service<Company> companyService;

    public AppHelperComputer(Input input, Service<Company> companyService) {
        this.input = input;
        this.companyService = companyService;
    }

    @Override
    public Computer create() {
        Computer computer = new Computer();
        try {
            System.out.print("Computer model name: ");
            computer.setModel(input.nextLine());
            companyService.printList();
            System.out.print("Add company to list? (y/n): ");
            String addCompanyChoose = input.nextLine();
            if(addCompanyChoose.equals("y")){
                System.out.println("Exiting task...");
                return null;
            }
            System.out.print("Company name(s): ");
            int countComputerCompanies = Integer.parseInt(input.nextLine());
            for (int i = 0; i < countComputerCompanies; i++){
                System.out.printf("Choose company number (%d company out of %d%n): ", i+1,countComputerCompanies);
                int numberCompany = Integer.parseInt(input.nextLine());
                computer.getCompanies().add(companyService.list().get(numberCompany-1));
            }
            System.out.print("Year of creation: ");
            computer.setReleaseYear(Integer.parseInt(input.nextLine()));
            return computer;

        }catch (Exception e){
            return null;
        }
    }




    @Override
    public boolean printList(List<Computer> computers) {
        try {
            if(computers.size() == 0) return false;
            for(int i = 0; i < computers.size(); i++){
                StringBuilder sbCompanies = new StringBuilder();
                for (int j = 0; j < computers.get(i).getCompanies().size(); j++) {
                    sbCompanies.append(computers.get(i).getCompanies().get(j).getName());
                    sbCompanies.append(" . ");
                }
                System.out.printf("%d. %s. %s%d%n", i+1,computers.get(i).getModel(),sbCompanies.toString(),computers.get(i).getReleaseYear());
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }
}
