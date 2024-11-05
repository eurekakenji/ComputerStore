package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.interfaces.Repository;


import java.util.List;

public class ComputerService implements Service {

    private Repository<Computer> repository;
    private AppHelper <Computer> computerAppHelper;

    public ComputerService(AppHelper<Computer> computerAppHelper, Repository<Computer> repository) {
        this.computerAppHelper = computerAppHelper;
        this.repository = repository;
    }
    public boolean add(){
        try {
            Computer computer = computerAppHelper.create();
            if (computer == null) return false;
            repository.save(computer);
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }

    }

    @Override
    public boolean print() {
        return computerAppHelper.printList(repository.load());
    }

    @Override
    public boolean printList() {
        return false;
    }

    @Override
    public List list() {
        return repository.load();
    }

    @Override
    public boolean edit(){
        List<Computer> modifiedComputers = computerAppHelper.edit(repository.load());
        if (modifiedComputers == null) {
            return false;
        }
        repository.saveAll(modifiedComputers);
        return true;
    }
}