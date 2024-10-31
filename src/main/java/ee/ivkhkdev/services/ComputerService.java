package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.repository.Repository;


import java.util.List;

public class ComputerService implements Service{

    private final List<Computer> computers;
    private Repository<Computer> repository;
    private AppHelper <Computer> appHelperComputer;

    public ComputerService(List<Computer> computers, AppHelper appHelperBook, Repository<Computer> repository) {
        this.computers = computers;
        this.appHelperComputer = appHelperBook;
        this.repository = repository;
    }
    public boolean add(){
        try {
            Computer book = appHelperComputer.create();
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
        return appHelperComputer.printList(computers);
    }

    @Override
    public List list() {
        return computers;
    }

    @Override
    public boolean printList() {
        return false;
    }
}