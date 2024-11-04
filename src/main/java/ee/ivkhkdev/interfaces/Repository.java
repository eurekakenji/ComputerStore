package ee.ivkhkdev.interfaces;

import java.util.List;

public interface Repository<T> {
    void save(T entity);
    List<T> load();

}
