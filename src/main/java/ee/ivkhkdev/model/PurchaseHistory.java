package ee.ivkhkdev.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class PurchaseHistory implements Serializable {
    private UUID id;
    private Computer computer;
    private User user;
    private LocalDate purchases;

    public PurchaseHistory() {
        this.id = UUID.randomUUID();
    }

    public PurchaseHistory(Computer computer, User user, LocalDate purchases) {
        this.id = UUID.randomUUID();
        this.computer = computer;
        this.user = user;
        this.purchases = purchases;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getPurchases() {
        return purchases;
    }

    public void setPurchases(LocalDate purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseHistory that = (PurchaseHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(computer, that.computer) && Objects.equals(user, that.user) && Objects.equals(purchases, that.purchases);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(computer);
        result = 31 * result + Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(purchases);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LibraryCard{");
        sb.append("id=").append(id);
        sb.append(", computer=").append(computer);
        sb.append(", user=").append(user);
        sb.append(", purchase=").append(purchases);
        sb.append('}');
        return sb.toString();
    }
}
