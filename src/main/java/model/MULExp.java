package model;

import exceptions.MyException;

public class MULExp implements Exp{
    private Exp exp1;
    private Exp exp2;

    public MULExp(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Exp newExpression = new ArithExp('-',
                new ArithExp('*', exp1, exp2), new ArithExp('+', exp1, exp2));
        Value ee = newExpression.eval(tbl, hp);
        return ee;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(exp1.typecheck(typeEnv).equals(new IntType()) && exp2.typecheck(typeEnv).equals(new IntType()))
            return new IntType();
        else
            throw new MyException("mullll");
    }

    @Override
    public Exp deepCopy() {
        return new MULExp(exp1.deepCopy(), exp2.deepCopy());
    }
}
