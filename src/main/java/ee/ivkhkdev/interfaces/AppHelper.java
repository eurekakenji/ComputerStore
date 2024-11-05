package ee.ivkhkdev.interfaces;

import java.util.List;

public interface AppHelper<T> {
    T create();
    boolean printList(List<T> listClazz);

    List<T> edit(List<T> load);
}