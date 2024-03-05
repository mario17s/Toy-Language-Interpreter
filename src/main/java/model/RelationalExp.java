package model;

import exceptions.MyException;

public class RelationalExp implements Exp{
    Exp e1;
    Exp e2;
    String op;

    public RelationalExp(String op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public String toString() {
        return String.valueOf(e1) + op + String.valueOf(e2);
    }


    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op.equals("<")) return new BoolValue(n1 < n2);//switch
                if (op.equals("<=")) return new BoolValue(n1 <= n2);
                if (op.equals("==")) return new BoolValue(n1 == n2);
                if (op.equals("!=")) return new BoolValue(n1 != n2);
                if (op.equals(">")) return new BoolValue(n1 > n2);
                if (op.equals(">=")) return new BoolValue(n1 >= n2);

            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
        throw new MyException("invalid evaluation");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType()))
                return new BoolType();
            else
                throw new MyException("second operand is not an integer");
        }
        else
            throw new MyException("first operand is not an integer");
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(op, e1.deepCopy(), e2.deepCopy());
    }
}
