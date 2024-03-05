package model;

import exceptions.MyException;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException;
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
     Exp deepCopy();
}
