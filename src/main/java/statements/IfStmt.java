package statements;

import exceptions.MyException;
import model.*;
import state.PrgState;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    public String toString(){
        return "(IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIStack<IStmt> stk = state.getExeStack();
        Value cond = exp.eval(symTbl, state.getHeap());

        if(!cond.getType().equals(new BoolType()))
            throw new MyException("Conditional expression is not a boolean");
        else{
            if(Boolean.parseBoolean(cond.toString()) == Boolean.TRUE)
                stk.push(thenS);
            else
                stk.push(elseS);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.clone());
            elseS.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
