package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.interfaces.Repository;


import java.util.List;

public class ComputerService implements Service {

    private final List<Computer> computers;
    private Repository<Computer> repository;
    private AppHelper <Computer> computerAppHelper;

    public ComputerService(List<Computer> computers, AppHelper computerAppHelper, Repository<Computer> repository) {
        this.computers = computers;
        this.computerAppHelper = computerAppHelper;
        this.repository = repository;
    }
    public boolean add(){
        try {
            Computer book = computerAppHelper.create();
            if(book == null) return false;
            for (int i = 0; i <= computers.size(); i++){
                if(i == 0 ){
                    computers.add(book);
                    repository.save(book);
                    break;
                }else if(computers.get(i) == null){
                    computers.add(book);
                    repository.save(book);
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
        return computerAppHelper.printList(computers);
    }

    @Override
    public List<Computer> list() {
        return computers;
    }

    @Override
    public boolean printList() {
        return false;
    }
}