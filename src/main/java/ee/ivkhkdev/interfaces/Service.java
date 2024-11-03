package ee.ivkhkdev.interfaces;

import java.util.List;

public interface Service<T> {
    boolean add();
    boolean print();

    boolean printList();

    List<T> list();
}
