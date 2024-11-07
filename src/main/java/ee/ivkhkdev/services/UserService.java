package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.interfaces.Repository;


import java.util.List;

public class UserService implements Service {
    private final Repository<User> repository;
    private AppHelper<User> appHelperUser;

    public UserService(AppHelper<User> appHelperUser, Repository<User> repository) {
        this.appHelperUser = appHelperUser;
        this.repository = repository;
    }

    public boolean add() {
        User user = appHelperUser.create();
        if (user == null) return false;
        try {
            repository.save(user);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean print() {
        return appHelperUser.printList(repository.load());
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
        List<User> modifiedUsers = appHelperUser.edit(repository.load());
        if (modifiedUsers == null) {
            return false;
        }
        repository.saveAll(modifiedUsers);
        return true;
    }
}