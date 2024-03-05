package model;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{

    Stack<T> stack;

    public Stack<T> getStack() {
        return stack;
    }

    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public String toString() {
        return "stack=" + stack;
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public String display() {
        return stack.toString();
    }

}
