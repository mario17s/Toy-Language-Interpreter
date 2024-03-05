package model;

import exceptions.MyException;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    String op;

    public LogicExp(String op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public String toString() {
        return String.valueOf(e1) + String.valueOf(op) + String.valueOf(e2);
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value nr1 = e1.eval(tbl, hp);
        if (nr1.getType().equals(new BoolType())) {
            Value nr2 = e2.eval(tbl, hp);
            if(nr2.getType().equals(new BoolType())){
                BoolValue v1 = (BoolValue) nr1;
                BoolValue v2 = (BoolValue) nr2;
                if(op.equals("&&"))
                    return new BoolValue(v1.getVal() && v2.getVal());
                if(op.equals("||"))
                    return new BoolValue(v1.getVal() || v2.getVal());
            } else throw new MyException("Operand 2 is not boolean");
        } else throw new MyException("Operand 1 is not boolean");
        throw new MyException("Invalid expression");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if(typ1.equals(new BoolType())){
            if(typ2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("second operand is not a boolean");
        }
        else
            throw new MyException("first operand is not a boolean");
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(op, e1.deepCopy(), e2.deepCopy());
    }
}
