package model;

import java.util.Map;

public interface IFileTable<T, U>{
    void add(T filename, U filedescriptor);
    U getElementByKey(T key);
    boolean isDefined(T key);
    void update(T key, U newValue);
    void remove(T key);

    Map<T, U> getTable();
}
