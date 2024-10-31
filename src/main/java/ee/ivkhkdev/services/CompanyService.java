package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.repository.Repository;

import java.util.List;

public class CompanyService implements Service{

    private final List <Company> companies;
    private Repository <Company> repository;
    private AppHelper <Company> appHelperCompany;

    public CompanyService(List<Company> authors, AppHelper appHelperCompany, Repository<Company> repository) {
        this.companies = authors;
        this.appHelperCompany = appHelperCompany;
        this.repository = repository;
    }

    public boolean add(){
        Company company = appHelperCompany.create();
        if(company == null) return false;
        try {
            for (int i = 0; i <= companies.size(); i++){
                if(i == 0 ){
                    companies.add(company);
                    repository.save(company);
                    break;
                }else if(companies.get(i) == null){
                    companies.add(company);
                    repository.save(company);
                    break;
                }
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

    @Override
    public boolean print() {
        return false;
    }


    @Override
    public boolean printList() {
        return appHelperCompany.printList(companies);
    }

    public List<Company> list() {
        return companies;
    }
}
