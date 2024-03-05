package model;

import java.util.Stack;

public interface MyIStack<T>{
    T pop();
    void push(T v);

    boolean isEmpty();

    String display();

    Stack<T> getStack();
}
