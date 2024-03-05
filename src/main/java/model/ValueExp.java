package model;

import exceptions.MyException;

public class ValueExp implements Exp{
    Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return String.valueOf(e);
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        return e;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(e.deepCopy());
    }
}
