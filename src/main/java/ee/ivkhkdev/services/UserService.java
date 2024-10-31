package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.repository.Repository;


import java.util.List;

public class UserService implements Service{
    private final List<User> users;
    private final Repository<User> repository;
    private AppHelper <User> appHelperUser;

    public UserService(List<User> users, AppHelper appHelperUser, Repository<User> repository) {
        this.users = users;
        this.appHelperUser = appHelperUser;
        this.repository = repository;
    }

    public boolean add() {
        User user = appHelperUser.create();
        if(user == null ) return false;
        for (int i = 0; i <= users.size(); i++){
            if(i == 0 ){
                users.add(user);
                repository.save(user);
                break;
            }else if(users.get(i) == null) {
                users.add(user);
                repository.save(user);
                break;
            }
        }
        return true;
    }

    @Override
    public boolean print() {
        return false;
    }

    @Override
    public List list() {
        return users;
    }

    public boolean printList() {
        return appHelperUser.printList(users);
    }
}