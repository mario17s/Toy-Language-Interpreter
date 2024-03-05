package model;

import java.util.HashMap;
import java.util.Map;

public class FileTable<T,U> implements IFileTable<T,U>{
    Map<T, U> table;

    public FileTable() {
        this.table = new HashMap<>();
    }

    public Map<T, U> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return table.keySet().toString();
    }

    @Override
    public void add(T filename, U filedescriptor) {
        table.put(filename, filedescriptor);
    }

    @Override
    public U getElementByKey(T key) {
        return table.get(key);
    }

    @Override
    public boolean isDefined(T key) {
        return table.containsKey(key);
    }

    @Override
    public void update(T key, U newValue) {
        table.replace(key, newValue);
    }

    @Override
    public void remove(T key) {
        table.remove(key);
    }
}
