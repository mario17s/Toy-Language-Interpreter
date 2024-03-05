package model;

import exceptions.MyException;

public class NotExp implements Exp{
    Exp exp;

    public NotExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value val = exp.eval(tbl, hp);
        BoolValue ll = (BoolValue) val;
        if(ll.getVal())
            return new BoolValue(false);
        else
            return new BoolValue(true);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return exp.typecheck(typeEnv);
    }

    @Override
    public Exp deepCopy() {
        return new NotExp(exp);
    }

    public String toString(){
        return "not " + exp.toString();
    }
}
