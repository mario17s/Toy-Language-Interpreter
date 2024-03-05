package model;

import java.util.Map;

public interface MyIHeap<T,U> {
    Integer add(U value);
    String display();

    void setHeap(Map<Integer, U> heap);
    Map<Integer, U> getHeap();

    U getElementByKey(T key);
    boolean isDefined(T key);
    void update(T key, U newValue);

}
