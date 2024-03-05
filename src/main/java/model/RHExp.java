package model;

import exceptions.MyException;

public class RHExp implements Exp{
    Exp expression;

    public RHExp(Exp expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "RHExp{" +
                "expression=" + expression +
                '}';
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value v = expression.eval(tbl, hp);
        if(!(v instanceof  RefValue))
            throw new MyException("Expression value must be a RefValue");
        int address = ((RefValue) v).getAddress();
        if(!hp.isDefined(address))
            throw new MyException("Address not found in heap");
        return hp.getElementByKey(address);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ=expression.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }

    @Override
    public Exp deepCopy() {
        return new RHExp(expression.deepCopy());
    }
}
