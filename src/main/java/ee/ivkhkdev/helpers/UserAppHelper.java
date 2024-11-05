package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.model.User;

import java.util.List;

public class UserAppHelper implements AppHelper<User> {
    private final Input input;


    public UserAppHelper(Input input) {
        this.input = input;

    }

    @Override
    public User create() {
        try {
            User user = new User();
            System.out.print("User name: ");
            user.setFirstName(input.nextLine());
            System.out.print("User surname: ");
            user.setLastName(input.nextLine());
            System.out.print("phone: ");
            user.setPhone(input.nextLine());
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList(List<User>users) {
        try {
            if(users.size() == 0) return false;
            for(int i = 0; i < users.size(); i++){
                System.out.printf("%d. %s %s. %s%n",
                        i+1,
                        users.get(i).getFirstName(),
                        users.get(i).getLastName(),
                        users.get(i).getPhone()
                );
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

    @Override
    public List<User> edit(List<User> load) {
        return List.of();
    }
}