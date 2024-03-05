package model;

import java.util.List;

public interface MyIList<T>{
    void add(T v);
    String display();

    List<T> getList();
}
