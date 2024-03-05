package model;

import java.util.Map;

public interface MyIDictionary<T, U>{
    void add(T key, U value);
    String display();
    U getElementByKey(T key);
    boolean isDefined(T key);
    void update(T key, U newValue);

    Map<T, U> getDictionary();
    MyIDictionary<T, U> clone();
}
