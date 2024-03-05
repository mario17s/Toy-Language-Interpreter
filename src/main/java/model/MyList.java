package model;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{

    List<T> list;

    public MyList() {
        list = new ArrayList<T>();
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "list=" + list;
    }

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public String display() {
        return list.toString();
    }
}
