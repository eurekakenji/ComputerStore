package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Computer;
import ee.ivkhkdev.model.PurchaseHistory;
import ee.ivkhkdev.model.User;

import java.time.LocalDate;
import java.util.List;

public class PurchaseHistoryAppHelper implements AppHelper<PurchaseHistory> {
    private final Input input;
    private final Service<Computer> computerService;
    private final Service<User> userService;

    public PurchaseHistoryAppHelper(Input input, Service<Computer> computerService, Service<User> userService) {
        this.input = input;
        this.computerService = computerService;
        this.userService = userService;
    }

    @Override
    public PurchaseHistory create() {
        if (!computerService.print()) {
            return null;
        }
        ;
        System.out.print("Enter computer number: ");
        int numberComputer = Integer.parseInt(input.nextLine());
        Computer computer = computerService.list().get(numberComputer - 1);
        if (!userService.print()) {
            return null;
        }
        System.out.print("Choose customer number: ");
        int numberUser = Integer.parseInt(input.nextLine());
        User user = userService.list().get(numberUser - 1);
        PurchaseHistory libraryCard = new PurchaseHistory();
        libraryCard.setComputer(computer);
        libraryCard.setUser(user);
        libraryCard.setPurchases(LocalDate.now());
        return libraryCard;
    }

    @Override
    public boolean printList(List<PurchaseHistory> purchaseHistories) {
        int count = 0;
        for (int i = 0; i < purchaseHistories.size(); i++) {
            PurchaseHistory purchaseHistory= purchaseHistories.get(i);
            if (purchaseHistory.getPurchases() == null) {
                System.out.printf("%d. %s. %s. Bought by %s %s%n",
                        i + 1,
                        purchaseHistory.getComputer().getModel(),
                        purchaseHistory.getComputer().getReleaseYear(),
                        purchaseHistory.getUser().getFirstName(),
                        purchaseHistory.getUser().getLastName()
                );
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<PurchaseHistory> edit(List<PurchaseHistory> load) {
        return List.of();
    }

    public List<PurchaseHistory> returnBack(List<PurchaseHistory> purchaseHistoryList) {
        return purchaseHistoryList;
    }
}
