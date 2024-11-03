package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.Company;

import java.util.List;

public class CompanyAppHelper implements AppHelper<Company> {

    private final Input input;


    public CompanyAppHelper(Input input) {
        this.input = input;

    }

    @Override
    public Company create() {
        Company company = new Company();
        try {
            System.out.print("Company Name: ");
            company.setName(input.nextLine());
            return company;
        }catch (Exception e){
            return null;
        }
    }



    @Override
    public boolean printList(List<Company> companies) {
        try {
            if(companies.size() < 1) return false;
            for(int i = 0; i < companies.size(); i++){
                System.out.printf("%d. %s %s%n", i+1,companies.get(i).getName());
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }
}
