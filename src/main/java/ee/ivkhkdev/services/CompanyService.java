package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Company;
import ee.ivkhkdev.interfaces.Repository;

import java.util.List;

public class CompanyService implements Service {
    ;
    private Repository <Company> repository;
    private AppHelper <Company> appHelperCompany;

    public CompanyService(AppHelper<Company> appHelperCompany, Repository<Company> repository) {
        this.appHelperCompany = appHelperCompany;
        this.repository = repository;
    }

    public boolean add(){
        Company company = appHelperCompany.create();
        if(company == null) return false;
        try{
            repository.save(company);
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
        return appHelperCompany.printList(repository.load());
    }

    public List<Company> list() {
        return repository.load();
    }

    @Override
    public boolean edit() {
        return false;
    }
}
