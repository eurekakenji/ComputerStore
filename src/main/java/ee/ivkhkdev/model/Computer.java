package ee.ivkhkdev.model;

import java.io.Serializable;
import java.util.*;

public class Computer  implements Serializable {
    private UUID id;
    private String model;
    private List<Company> companies = new ArrayList<>();
    private int releaseYear;

    public Computer() {
        this.id = UUID.randomUUID();
    }

    public Computer(String model, List<Company> companies, int releaseYear) {
        this.id = UUID.randomUUID();
        this.model = model;
        this.companies = companies;
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setAuthors(List<Company> companies) {
        this.companies = companies;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;
        return releaseYear == computer.releaseYear && Objects.equals(id, computer.id) && Objects.equals(model, computer.model) && Arrays.equals(companies.toArray(), computer.companies.toArray());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(model);
        result = 31 * result + Arrays.hashCode(companies.toArray());
        result = 31 * result + releaseYear;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", model='").append(model).append('\'');
        sb.append(", companies=").append(Arrays.toString(companies.toArray()));
        sb.append(", ReleaseYear=").append(releaseYear);
        sb.append('}');
        return sb.toString();
    }

}

